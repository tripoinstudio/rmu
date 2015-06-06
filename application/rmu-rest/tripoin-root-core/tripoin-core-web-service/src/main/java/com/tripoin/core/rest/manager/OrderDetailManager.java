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
import org.springframework.integration.message.GenericMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.tripoin.core.pojo.VersionFilter;
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
	
	private String currentUserName;
	
	private Date currentDate;
	
	@SuppressWarnings("unchecked")
	@Secured({"ROLE_WAITRESS", "ROLE_TRAIN"})
	public Message<?> getOrderDetails(Message<?> inMessage){
		String orderHeaderNo = "";
		try{
			Map<String, List<String>> payloadMap = (Map<String, List<String>>)inMessage.getPayload();
			if(payloadMap != null){
				if(payloadMap.containsKey("orderHeaderNo"))
					orderHeaderNo = payloadMap.get("orderHeaderNo").get(0).toString();
			}			
		}catch(Exception e){
			LOGGER.error("Error :".concat(e.getLocalizedMessage()), e);
		}
		return getListOrderDetails(orderHeaderNo);
	}
	
	@SuppressWarnings("unchecked")
	@Secured({"ROLE_WAITRESS", "ROLE_TRAIN"})
	public Message<OrderDetails> setOrderDetails(Message<?> inMessage){	
		currentDate = new Date();
		Message<OrderDetails> message = null;
		try{
			String jsonOrderDetail = null;
			try{
				Map<String, List<String>> payloadMap = (Map<String, List<String>>)inMessage.getPayload();
				if(payloadMap != null){
					if(payloadMap.containsKey("trx_order_detail"))
						jsonOrderDetail = payloadMap.get("trx_order_detail").get(0).toString();
				}			
			}catch(Exception e){
				LOGGER.error("Error :".concat(e.getLocalizedMessage()), e);
			}

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (!(authentication instanceof AnonymousAuthenticationToken)) {
			    currentUserName = authentication.getName();
			}
			
			ObjectMapper om = new ObjectMapper();
			OrderDetails orderDetails = om.readValue(jsonOrderDetail, OrderDetails.class);
			List<OrderDetailDTO> orderDetailDTOList = orderDetails.getTrx_order_detail();
			
			OrderHeader orderHeader = new OrderHeader();
			List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
			
			List<Menu> menuList = iGenericManagerJpa.loadObjects(Menu.class);
			Map<Integer, BigDecimal> mapPrice = new HashMap<Integer, BigDecimal>();
			for (Menu menu : menuList) mapPrice.put(menu.getId(), menu.getPrice());
			BigDecimal totalAmount = new BigDecimal(0);
			BigDecimal totalPaid = new BigDecimal(0);			
			boolean isOrderHeader = true;
			
			Menu menu = new Menu();
			Seat seat = new Seat();
			Carriage carriage = new Carriage();
			Train train = new Train();
			
			for (OrderDetailDTO orderDetailDTO : orderDetailDTOList) { 
				// Order Header
				if(isOrderHeader){
					seat = iGenericManagerJpa.getObjectsUsingParameter(Seat.class, new String[]{"code"}, new Object[]{orderDetailDTO.getSeat_code()}, null, null).get(0);
					carriage = iGenericManagerJpa.getObjectsUsingParameter(Carriage.class, new String[]{"code"}, new Object[]{orderDetailDTO.getCarriage_code()}, null, null).get(0);
					train = iGenericManagerJpa.getObjectsUsingParameter(Train.class, new String[]{"code"}, new Object[]{orderDetailDTO.getTrain_code()}, null, null).get(0);
					User user = iGenericManagerJpa.getObjectsUsingParameter(User.class, new String[]{"username"}, new Object[]{currentUserName}, null, null).get(0);

					Stan stan = stanGenerator.getSystemTraceAuditNumber(Long.parseLong(user.getId().toString()));
					String dateFormat = new SimpleDateFormat("yyyyMMdd").format(currentDate);
					
					orderHeader.setOrderNo("OR".concat(dateFormat).concat(leftPadding(stan.getStanCounter(), 5, "0")));
					orderHeader.setSeat(seat);
					orderHeader.setUser(user);
					orderHeader.setCarriage(carriage);
					orderHeader.setTrain(train);
					orderHeader.setVersionTimestamp(currentDate);
					orderHeader.setOrderDatetime(currentDate);
					orderHeader.setIsArchive(0);
					orderHeader.setStatus(orderDetailDTO.getOrder_header_status());
					orderHeader.setRemarks("ORDER");
					
					isOrderHeader = false;
				}				
				
				//Order Detail
				OrderDetail order = new OrderDetail();
				menu = iGenericManagerJpa.getObjectsUsingParameter(Menu.class, new String[]{"code"}, new Object[]{orderDetailDTO.getMenu_code()}, null, null).get(0);				
				totalAmount = mapPrice.get(menu.getId()).multiply(new BigDecimal(orderDetailDTO.getOrder_detail_total_order()));
				totalPaid = totalPaid.add(totalAmount);
				if((menu.getStock()-orderDetailDTO.getOrder_detail_total_order()) > 0){
					menu.setStock(menu.getStock()-orderDetailDTO.getOrder_detail_total_order());
				}else{
					menu.setStock(0);					
				}
				iGenericManagerJpa.updateObject(menu);
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
			List<VersionFilter> versionFilterList = iGenericManagerJpa.getObjectsUsingParameter(VersionFilter.class, new String[]{"user.username"}, new Object[]{currentUserName}, null, null);
			VersionFilter versionFilter = versionFilterList.get(0);
			versionFilter.setVersionOrderHeader(currentDate);
			iGenericManagerJpa.updateObject(versionFilter);
			iGenericManagerJpa.saveObject(orderHeader);
			iVersionHelper.updateVerision("trx_order_header", currentDate);
			iVersionHelper.updateVerision("trx_order_detail", currentDate);
			iVersionHelper.updateVerision("master_menu", currentDate);
			message = getListOrderDetails(orderHeader.getOrderNo());
		}catch(Exception e){
			LOGGER.error("Error :"+e.getMessage(), e);
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
					OrderDetailDTO data = new OrderDetailDTO(o.getOrderHeader().getOrderNo(), o.getTotalOrder(), o.getTotalAmount(), o.getOrderHeader().getStatus(), o.getMenu().getCode(), o.getMenu().getName(),  o.getOrderHeader().getSeat().getCode(), o.getOrderHeader().getCarriage().getCode(), o.getOrderHeader().getTrain().getCode());

					orderDetailDTOList.add(data);
				} 
				orderDetails.setTrx_order_detail(orderDetailDTOList);
				LOGGER.debug("data : "+orderDetails.toString());
				isFound = true;
			}else{				
				isFound = false;
			}			
			if (isFound){
				setReturnStatusAndMessage("0", "Load Data Success", "SUCCESS", orderDetails, responseHeaderMap);
			}else{
				setReturnStatusAndMessage("2", "Order Detail Not Found", "EMPTY", orderDetails, responseHeaderMap);								
			}
			
		}catch (Exception e){
			setReturnStatusAndMessage("1", "System Error : "+e.getMessage(), "FAILURE", orderDetails, responseHeaderMap);
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
