package com.tripoin.core.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "security_user", "trx_order_header", "master_version", "response_code", "response_msg", "result" })
@XmlRootElement(name = "OrderHeaderWithUser")
public class OrderHeaderWithUsers {		
	@XmlElement(name = "User", required = true)
	private UserDTO security_user;
	
	@XmlElement(name = "OrderHeader", required = true)
	private List<OrderHeaderDTO> trx_order_header;
	
	@XmlElement(name = "Version", required = true)
	private List<VersionDTO> master_version;
	
	@XmlElement(name = "response_code", required = true)
	private String response_code;
	
	@XmlElement(name = "response_msg", required = true)
	private String response_msg;
	
	@XmlElement(name = "result", required = true)
	private String result;

	public UserDTO getSecurity_user() {
		return security_user;
	}

	public void setSecurity_user(UserDTO security_user) {
		this.security_user = security_user;
	}

	public List<OrderHeaderDTO> getTrx_order_header() {
		return trx_order_header;
	}

	public void setTrx_order_header(List<OrderHeaderDTO> trx_order_header) {
		this.trx_order_header = trx_order_header;
	}

	public List<VersionDTO> getMaster_version() {
		return master_version;
	}

	public void setMaster_version(List<VersionDTO> master_version) {
		this.master_version = master_version;
	}

	public String getResponse_code() {
		return response_code;
	}

	public void setResponse_code(String response_code) {
		this.response_code = response_code;
	}

	public String getResponse_msg() {
		return response_msg;
	}

	public void setResponse_msg(String response_msg) {
		this.response_msg = response_msg;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "OrderHeaderWithUsers [security_user=" + security_user
				+ ", trx_order_header=" + trx_order_header
				+ ", master_version=" + master_version + ", response_code="
				+ response_code + ", response_msg=" + response_msg
				+ ", result=" + result + "]";
	}
}
