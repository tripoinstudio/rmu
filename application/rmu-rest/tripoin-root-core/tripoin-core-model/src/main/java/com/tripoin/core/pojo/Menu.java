package com.tripoin.core.pojo;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Entity
@Table(name="master_menu")
public class Menu {
	private Integer id;
	private String code;
	private String name;
	private Integer type;
	private BigDecimal price;
	private BigDecimal rating;
	private Integer status;
	private String remarks;
	private List<OrderDetail> orderDetails;
	private List<Image> images;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="menu_id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="menu_code", length=50)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name="menu_name", length=100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="menu_type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name="menu_price")
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name="menu_rating")
	public BigDecimal getRating() {
		return rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}

	@Column(name="menu_status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name="menu_remarks", length=255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "menu", cascade=CascadeType.ALL)
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "menu", cascade=CascadeType.ALL)
	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", code=" + code + ", name=" + name
				+ ", type=" + type + ", price=" + price
				+ ", rating=" + rating + ", status=" + status
				+ ", remarks=" + remarks + "]";
	}
	
}
