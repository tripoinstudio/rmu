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
@XmlType(name = "", propOrder = { "order_header_no", "order_detail_total_order", "order_detail_total_amount", "order_header_status", "user_code", "menu_id", "menu_name", "seat_id", "carriage_id", "train_id" })
@XmlRootElement(name = "OrderDetail")
public class OrderDetailDTO {
	private String order_header_no;
	private Integer order_detail_total_order;
	private BigDecimal order_detail_total_amount;
	private Integer order_header_status;
	private String user_code;
	private Integer menu_id;
	private String menu_name;
	private Integer seat_id;
	private Integer carriage_id;
	private Integer train_id;
	
	public OrderDetailDTO() {}
	
	public OrderDetailDTO(String order_header_no,
			Integer order_detail_total_order,
			BigDecimal order_detail_total_amount, Integer order_header_status,
			String user_code, Integer menu_id, String menu_name,
			Integer seat_id, Integer carriage_id, Integer train_id) {
		super();
		this.order_header_no = order_header_no;
		this.order_detail_total_order = order_detail_total_order;
		this.order_detail_total_amount = order_detail_total_amount;
		this.order_header_status = order_header_status;
		this.user_code = user_code;
		this.menu_id = menu_id;
		this.menu_name = menu_name;
		this.seat_id = seat_id;
		this.carriage_id = carriage_id;
		this.train_id = train_id;
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

	public Integer getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(Integer menu_id) {
		this.menu_id = menu_id;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public Integer getSeat_id() {
		return seat_id;
	}

	public void setSeat_id(Integer seat_id) {
		this.seat_id = seat_id;
	}

	public Integer getCarriage_id() {
		return carriage_id;
	}

	public void setCarriage_id(Integer carriage_id) {
		this.carriage_id = carriage_id;
	}

	public Integer getTrain_id() {
		return train_id;
	}

	public void setTrain_id(Integer train_id) {
		this.train_id = train_id;
	}

	@Override
	public String toString() {
		return "OrderDetailDTO [order_header_no=" + order_header_no
				+ ", order_detail_total_order=" + order_detail_total_order
				+ ", order_detail_total_amount=" + order_detail_total_amount
				+ ", order_header_status=" + order_header_status
				+ ", user_code=" + user_code + ", menu_id=" + menu_id
				+ ", menu_name=" + menu_name + ", seat_id=" + seat_id
				+ ", carriage_id=" + carriage_id + ", train_id=" + train_id
				+ "]";
	}
}
