package com.tripoin.core.domain;

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
@XmlType(name = "", propOrder = { "user", "responseCode", "responseMsg", "result" })
@XmlRootElement(name = "Users")
public class Users {
	@XmlElement(name = "User", required = true)
	private List<UserDTO> security_user;
	
	@XmlElement(name = "responseCode", required = true)
	private String response_code;
	
	@XmlElement(name = "responseMsg", required = true)
	private String response_msg;
	
	@XmlElement(name = "result", required = true)
	private String RESULT;

	public List<UserDTO> getSecurity_user() {
		return security_user;
	}

	public void setSecurity_user(List<UserDTO> security_user) {
		this.security_user = security_user;
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

	public String getRESULT() {
		return RESULT;
	}

	public void setRESULT(String rESULT) {
		RESULT = rESULT;
	}

	@Override
	public String toString() {
		return "Users [security_user=" + security_user + ", response_code="
				+ response_code + ", response_msg=" + response_msg
				+ ", RESULT=" + RESULT + "]";
	}
}
