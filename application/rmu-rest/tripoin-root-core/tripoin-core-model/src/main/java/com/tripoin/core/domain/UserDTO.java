package com.tripoin.core.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "id", "username", "password", "status", "remarks" })
@XmlRootElement(name = "User")
public class UserDTO {
	private Integer user_id;
	private String user_code;
	private String user_password;
	private Integer user_status;
	private String user_remarks;

	public UserDTO() {}
	
	public UserDTO(Integer user_id, String user_code, String user_password,
			Integer user_status, String user_remarks) {
		super();
		this.user_id = user_id;
		this.user_code = user_code;
		this.user_password = user_password;
		this.user_status = user_status;
		this.user_remarks = user_remarks;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public Integer getUser_status() {
		return user_status;
	}

	public void setUser_status(Integer user_status) {
		this.user_status = user_status;
	}

	public String getUser_remarks() {
		return user_remarks;
	}

	public void setUser_remarks(String user_remarks) {
		this.user_remarks = user_remarks;
	}

	@Override
	public String toString() {
		return "UserDTO [user_id=" + user_id + ", user_code=" + user_code
				+ ", user_password=" + user_password + ", user_status="
				+ user_status + ", user_remarks=" + user_remarks + "]";
	}
}
