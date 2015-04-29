package com.tripoin.core.rest.manager;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.MessageHeaders;
import org.springframework.integration.message.GenericMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.tripoin.core.dto.OrderDetailDTO;
import com.tripoin.core.dto.OrderDetails;
import com.tripoin.core.pojo.Carriage;
import com.tripoin.core.pojo.Menu;
import com.tripoin.core.pojo.OrderDetail;
import com.tripoin.core.pojo.OrderHeader;
import com.tripoin.core.pojo.Seat;
import com.tripoin.core.pojo.Stan;
import com.tripoin.core.pojo.Train;
import com.tripoin.core.pojo.User;
import com.tripoin.core.rest.util.IVersionHelper;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.core.service.util.IStanGenerator;

@Service("orderDetailManager")
public class OrderDetailManager {
	private static transient final Logger LOGGER = LoggerFactory.getLogger(OrderDetailManager.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	@Autowired
	private IStanGenerator stanGenerator;

	@Autowired
	private IVersionHelper iVersionHelper;
	
	@Secured("ROLE_REST_HTTP_USER")
	public Message<OrderDetails> getOrderDetails(Message<?> inMessage){
		String orderHeaderNo = "";
		try{
			MessageHeaders headers = inMessage.getHeaders();
			orderHeaderNo = (String) headers.get("orderHeaderNo");			
		}catch(Exception e){
			LOGGER.error("Error :".concat(e.getLocalizedMessage()), e);
		}
		return getListOrderDetails(orderHeaderNo);
	}
	
	@Secured("ROLE_REST_HTTP_USER")
	public Message<OrderDetails> setOrderDetails(Message<?> inMessage){	
		Message<OrderDetails> message = null;
		try{
			MessageHeaders headers = inMessage.getHeaders();
			String jsonOrderDetail = (String)headers.get("jsonOrderDetail");
			ObjectMapper om = new ObjectMapper();
			OrderDetails orderDetails = om.readValue(jsonOrderDetail, OrderDetails.class);
			List<OrderDetailDTO> orderDetailDTOList = orderDetails.getTrx_order_detail();
			
			boolean isOrderHeader = true;
			
			OrderHeader orderHeader = new OrderHeader();
			List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
			List<Menu> menuList = iGenericManagerJpa.loadObjects(Menu.class);
			Map<Integer, BigDecimal> mapPrice = new HashMap<Integer, BigDecimal>();
			for (Menu menu : menuList) {
				mapPrice.put(menu.getId(), menu.getPrice());
			}
			Menu menu = new Menu();
			BigDecimal totalAmount = new BigDecimal(0);
			BigDecimal totalPaid = new BigDecimal(0);
			
			for (OrderDetailDTO orderDetailDTO : orderDetailDTOList) {
				// Order Header
				if(isOrderHeader){
					Seat seat = new Seat();
					seat.setId(orderDetailDTO.getSeat_id());
					Carriage carriage = new Carriage();
					carriage.setId(orderDetailDTO.getCarriage_id());
					Train train = new Train();
					train.setId(orderDetailDTO.getTrain_id());
					menu.setId(orderDetailDTO.getMenu_id());
					
					User user = iGenericManagerJpa.getObjectsUsingParameter(User.class, new String[]{"username"}, new Object[]{orderDetailDTO.getUser_code()}, null, null).get(0);

					Stan stan = stanGenerator.getSystemTraceAuditNumber(Long.parseLong(user.getId().toString()));
					String dateFormat = new SimpleDateFormat("yyyyMMdd").format(new Date());
					
					orderHeader.setOrderNo("OR".concat(dateFormat).concat(leftPadding(stan.getStanCounter(), 5, "0")));
					orderHeader.setSeat(seat);
					orderHeader.setUser(user);
					orderHeader.setCarriage(carriage);
					orderHeader.setTrain(train);
					orderHeader.setOrderDatetime(new Date());
					orderHeader.setIsArchive(0);
					orderHeader.setStatus(1);
					orderHeader.setRemarks("ORDER");
					
					isOrderHeader = false;
				}
				totalAmount = mapPrice.get(orderDetailDTO.getMenu_id()).multiply(new BigDecimal(orderDetailDTO.getOrder_detail_total_order()));
				totalPaid = totalPaid.add(totalAmount);
				
				//Order Detail
				OrderDetail order = new OrderDetail();
				
				order.setMenu(menu);
				order.setOrderHeader(orderHeader);
				order.setRemarks("ORDER");
				order.setStatus(1);
				order.setTotalAmount(totalAmount);
				order.setTotalOrder(orderDetailDTO.getOrder_detail_total_order());
				
				orderDetailList.add(order);
			}
			orderHeader.setTotalPaid(totalPaid);
			orderHeader.setOrderDetails(orderDetailList);
			iGenericManagerJpa.saveObject(orderHeader);
			iVersionHelper.updateVerision();
			message = getListOrderDetails(orderHeader.getOrderNo());
		}catch(Exception e){
			LOGGER.error("Error :".concat(e.getLocalizedMessage()), e);
		}
		return message;		
	}	
	
	public Message<OrderDetails> getListOrderDetails(String orderHeaderNo){
		
		OrderDetails orderDetails = new OrderDetails();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		
		try{
			List<OrderDetail> orderDetailList = iGenericManagerJpa.getObjectsUsingParameter(OrderDetail.class, new String[]{"orderHeader.orderNo"}, new Object[]{orderHeaderNo}, null, null);
			boolean isFound;
			if (orderDetailList!=null){
				List<OrderDetailDTO> orderDetailDTOList = new ArrayList<OrderDetailDTO>();
				for (OrderDetail o : orderDetailList) {
					LOGGER.debug("data :"+o.toString());
					OrderDetailDTO data = new OrderDetailDTO(o.getOrderHeader().getOrderNo(), o.getTotalOrder(), o.getTotalAmount(), o.getStatus(), o.getOrderHeader().getUser().getUsername(), o.getMenu().getId(), o.getMenu().getName(),  o.getOrderHeader().getSeat().getId(), o.getOrderHeader().getCarriage().getId(), o.getOrderHeader().getTrain().getId());
					orderDetailDTOList.add(data);
				} 
				orderDetails.setTrx_order_detail(orderDetailDTOList);
				isFound = true;
			}else{				
				isFound = false;
			}			
			if (isFound){
				setReturnStatusAndMessage("0", "Load Data Success", "SUCCESS", orderDetails, responseHeaderMap);
			}else{
				setReturnStatusAndMessage("2", "Seat Not Found", "EMPTY", orderDetails, responseHeaderMap);								
			}
			
		}catch (Exception e){
			setReturnStatusAndMessage("1", "System Error"+e.getMessage(), "FAILURE", orderDetails, responseHeaderMap);
		}
		Message<OrderDetails> message = new GenericMessage<OrderDetails>(orderDetails, responseHeaderMap);
		return message;		
	}	
	
	public String leftPadding(Long data, Integer paddingCount, String charPadding){
		return String.format("%".concat(charPadding)+paddingCount+"d",data);
	}
	
	private void setReturnStatusAndMessage(String responseCode, String responseMsg, String result, OrderDetails orderDetails, Map<String, Object> responseHeaderMap){		
		orderDetails.setResponse_code(responseCode);
		orderDetails.setResponse_msg(responseMsg);
		orderDetails.setResult(result);
		responseHeaderMap.put("Return-Status", responseCode);
		responseHeaderMap.put("Return-Status-Msg", responseMsg);
	}
}
