package com.tripoin.core.dto;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "order_header_no", "order_detail_total_order", "order_detail_total_amount", "order_header_status", "user_code", "menu_code", "menu_name", "seat_code", "carriage_code", "train_code" })
@XmlRootElement(name = "OrderDetail")
public class OrderDetailDTO {
	private String order_header_no;
	private Integer order_detail_total_order;
	private BigDecimal order_detail_total_amount;
	private Integer order_header_status;
	private String user_code;
	private String menu_code;
	private String menu_name;
	private String seat_code;
	private String carriage_code;
	private String train_code;
	
	public OrderDetailDTO() {}

	public OrderDetailDTO(String order_header_no,
			Integer order_detail_total_order,
			BigDecimal order_detail_total_amount, Integer order_header_status,
			String user_code, String menu_code, String menu_name,
			String seat_code, String carriage_code, String train_code) {
		super();
		this.order_header_no = order_header_no;
		this.order_detail_total_order = order_detail_total_order;
		this.order_detail_total_amount = order_detail_total_amount;
		this.order_header_status = order_header_status;
		this.user_code = user_code;
		this.menu_code = menu_code;
		this.menu_name = menu_name;
		this.seat_code = seat_code;
		this.carriage_code = carriage_code;
		this.train_code = train_code;
	}

	public String getOrder_header_no() {
		return order_header_no;
	}

	public void setOrder_header_no(String order_header_no) {
		this.order_header_no = order_header_no;
	}

	public Integer getOrder_detail_total_order() {
		return order_detail_total_order;
	}

	public void setOrder_detail_total_order(Integer order_detail_total_order) {
		this.order_detail_total_order = order_detail_total_order;
	}

	public BigDecimal getOrder_detail_total_amount() {
		return order_detail_total_amount;
	}

	public void setOrder_detail_total_amount(BigDecimal order_detail_total_amount) {
		this.order_detail_total_amount = order_detail_total_amount;
	}

	public Integer getOrder_header_status() {
		return order_header_status;
	}

	public void setOrder_header_status(Integer order_header_status) {
		this.order_header_status = order_header_status;
	}

	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public String getMenu_code() {
		return menu_code;
	}

	public void setMenu_code(String menu_code) {
		this.menu_code = menu_code;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public String getSeat_code() {
		return seat_code;
	}

	public void setSeat_code(String seat_code) {
		this.seat_code = seat_code;
	}

	public String getCarriage_code() {
		return carriage_code;
	}

	public void setCarriage_code(String carriage_code) {
		this.carriage_code = carriage_code;
	}

	public String getTrain_code() {
		return train_code;
	}

	public void setTrain_code(String train_code) {
		this.train_code = train_code;
	}

	@Override
	public String toString() {
		return "OrderDetailDTO [order_header_no=" + order_header_no
				+ ", order_detail_total_order=" + order_detail_total_order
				+ ", order_detail_total_amount=" + order_detail_total_amount
				+ ", order_header_status=" + order_header_status
				+ ", user_code=" + user_code + ", menu_code=" + menu_code
				+ ", menu_name=" + menu_name + ", seat_code=" + seat_code
				+ ", carriage_code=" + carriage_code + ", train_code="
				+ train_code + "]";
	}	
}
