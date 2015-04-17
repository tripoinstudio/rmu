package com.tripoin.core.pojo;

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
@Table(name="master_carriage")
public class Carriage {
	private Integer id;
	private String code;
	private String no;
	private Integer status;
	private String remarks;
	private List<OrderHeader> orderHeaders;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="carriage_id")
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="carriage_code", length = 50)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name="carriage_no", length = 2)
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	
	@Column(name="carriage_status")
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name="carriage_remarks", length = 255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "carriage", cascade=CascadeType.ALL)
	public List<OrderHeader> getOrderHeaders() {
		return orderHeaders;
	}

	public void setOrderHeaders(List<OrderHeader> orderHeaders) {
		this.orderHeaders = orderHeaders;
	}

	@Override
	public String toString() {
		return "Carriage [id=" + id + ", code=" + code + ", no=" + no
				+ ", status=" + status + ", remarks=" + remarks + "]";
	}
}
