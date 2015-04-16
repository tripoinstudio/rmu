package com.tripoin.core.pojo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
//@Entity
//@Table(name="trx_order_header")
public class OrderHeader {
	private String orderNo;
	private User user;
	private Carriage carriage;
	private Seat seat;
	private Date orderDatetime;
	private BigDecimal totalPaid;
	private Integer status;
	private String remarks;

//	@Id
//	@Column(name="order_header_no")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Carriage getCarriage() {
		return carriage;
	}

	public void setCarriage(Carriage carriage) {
		this.carriage = carriage;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

//	@Column(name="order_header_order_datetime")
	public Date getOrderDatetime() {
		return orderDatetime;
	}

	public void setOrderDatetime(Date orderDatetime) {
		this.orderDatetime = orderDatetime;
	}

//	@Column(name="order_header_total_paid")
	public BigDecimal getTotalPaid() {
		return totalPaid;
	}

	public void setTotalPaid(BigDecimal totalPaid) {
		this.totalPaid = totalPaid;
	}

//	@Column(name="order_header_status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

//	@Column(name="order_header_status", length=255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "OrderHeader [orderNo=" + orderNo + ", user=" + user
				+ ", carriage=" + carriage + ", seat=" + seat
				+ ", orderDatetime=" + orderDatetime + ", totalPaid="
				+ totalPaid + ", status=" + status + ", remarks=" + remarks
				+ "]";
	}
}
