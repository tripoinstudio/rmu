package com.tripoin.core.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "order_header_no", "order_header_order_datetime", "order_header_total_paid", "order_header_status", "seat_no", "carriage_no", "train_no" })
@XmlRootElement(name = "OrderHeader")
public class OrderHeaderDTO {
	private String order_header_no;
	private Date order_header_order_datetime;
	private BigDecimal order_header_total_paid;
	private Integer order_header_status;
	private Integer seat_no;
	private Integer carriage_no;
	private Integer train_no;
	
	public OrderHeaderDTO() {}

	public OrderHeaderDTO(String order_header_no,
			Date order_header_order_datetime,
			BigDecimal order_header_total_paid, Integer order_header_status,
			Integer seat_no, Integer carriage_no, Integer train_no) {
		super();
		this.order_header_no = order_header_no;
		this.order_header_order_datetime = order_header_order_datetime;
		this.order_header_total_paid = order_header_total_paid;
		this.order_header_status = order_header_status;
		this.seat_no = seat_no;
		this.carriage_no = carriage_no;
		this.train_no = train_no;
	}

	public String getOrder_header_no() {
		return order_header_no;
	}

	public void setOrder_header_no(String order_header_no) {
		this.order_header_no = order_header_no;
	}

	public Date getOrder_header_order_datetime() {
		return order_header_order_datetime;
	}

	public void setOrder_header_order_datetime(Date order_header_order_datetime) {
		this.order_header_order_datetime = order_header_order_datetime;
	}

	public BigDecimal getOrder_header_total_paid() {
		return order_header_total_paid;
	}

	public void setOrder_header_total_paid(BigDecimal order_header_total_paid) {
		this.order_header_total_paid = order_header_total_paid;
	}

	public Integer getOrder_header_status() {
		return order_header_status;
	}

	public void setOrder_header_status(Integer order_header_status) {
		this.order_header_status = order_header_status;
	}

	public Integer getSeat_no() {
		return seat_no;
	}

	public void setSeat_no(Integer seat_no) {
		this.seat_no = seat_no;
	}

	public Integer getCarriage_no() {
		return carriage_no;
	}

	public void setCarriage_no(Integer carriage_no) {
		this.carriage_no = carriage_no;
	}

	public Integer getTrain_no() {
		return train_no;
	}

	public void setTrain_no(Integer train_no) {
		this.train_no = train_no;
	}

	@Override
	public String toString() {
		return "OrderHeaderDTO [order_header_no=" + order_header_no
				+ ", order_header_order_datetime="
				+ order_header_order_datetime + ", order_header_total_paid="
				+ order_header_total_paid + ", order_header_status="
				+ order_header_status + ", seat_no=" + seat_no
				+ ", carriage_no=" + carriage_no + ", train_no=" + train_no
				+ "]";
	}	
}
