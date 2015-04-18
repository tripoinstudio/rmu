package com.tripoin.core.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Entity
@Table(name="trx_order_header")
public class OrderHeader {
	private String orderNo;
	private User user;
	private Train train;
	private Carriage carriage;
	private Seat seat;
	private Date orderDatetime;
	private BigDecimal totalPaid;
	private Integer isArchive;
	private Integer status;
	private String remarks;
	private List<OrderDetail> orderDetails;

	@Id
	@Column(name="order_header_no")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(name = "train_id", nullable = false)
	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	@ManyToOne
	@JoinColumn(name = "carriage_id", nullable = false)
	public Carriage getCarriage() {
		return carriage;
	}

	public void setCarriage(Carriage carriage) {
		this.carriage = carriage;
	}

	@ManyToOne
	@JoinColumn(name = "seat_id", nullable = false)
	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	@Column(name="order_header_order_datetime")
	public Date getOrderDatetime() {
		return orderDatetime;
	}

	public void setOrderDatetime(Date orderDatetime) {
		this.orderDatetime = orderDatetime;
	}

	@Column(name="order_header_total_paid")
	public BigDecimal getTotalPaid() {
		return totalPaid;
	}

	public void setTotalPaid(BigDecimal totalPaid) {
		this.totalPaid = totalPaid;
	}

	@Column(name="order_header_is_archive")
	public Integer getIsArchive() {
		return isArchive;
	}

	public void setIsArchive(Integer isArchive) {
		this.isArchive = isArchive;
	}

	@Column(name="order_header_status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name="order_header_remarks", length=255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderHeader", cascade=CascadeType.ALL)
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	@Override
	public String toString() {
		return "OrderHeader [orderNo=" + orderNo + ", user=" + user
				+ ", train=" + train + ", carriage=" + carriage + ", seat=" + seat
				+ ", orderDatetime=" + orderDatetime + ", totalPaid="
				+ totalPaid + ", isArchive=" + isArchive + ", status=" + status + ", remarks=" + remarks
				+ "]";
	}
}
