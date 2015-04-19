package com.tripoin.core.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "user_code", "role_code" })
@XmlRootElement(name = "User")
public class UserDTO {
	private String user_code;
	private String role_code;

	public UserDTO() {}

	public UserDTO(String user_code, String role_code) {
		super();
		this.user_code = user_code;
		this.role_code = role_code;
	}

	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public String getRole_code() {
		return role_code;
	}

	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}

	@Override
	public String toString() {
		return "UserDTO [user_code=" + user_code + ", role_code=" + role_code
				+ "]";
	}
}
