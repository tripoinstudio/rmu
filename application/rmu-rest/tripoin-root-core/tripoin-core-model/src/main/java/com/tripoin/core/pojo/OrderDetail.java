package com.tripoin.core.pojo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Entity
@Table(name="trx_order_detail")
public class OrderDetail {
	private Integer id;
	private Menu menu;
	private Integer orderAmount;
	private BigDecimal totalAmount;
	private OrderHeader orderHeader;
	private Integer status;
	private String remarks;
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="order_detail_id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id", nullable = false)
	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@Column(name="order_detail_order_amount")
	public Integer getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Integer orderAmount) {
		this.orderAmount = orderAmount;
	}

	@Column(name="order_detail_total_amount")
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_header_no", nullable = false)
	public OrderHeader getOrderHeader() {
		return orderHeader;
	}

	public void setOrderHeader(OrderHeader orderHeader) {
		this.orderHeader = orderHeader;
	}
	
	@Column(name="order_detail_status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name="order_detail_remarks", length=255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", menu=" + menu + ", orderAmount="
				+ orderAmount + ", totalAmount=" + totalAmount
				+ ", orderHeader=" + orderHeader + ", status=" + status
				+ ", remarks=" + remarks + "]";
	}	
}
