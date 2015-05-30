package com.tripoin.core.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Entity
@Table(name="security_role")
public class Role {
	
	private Integer id;
	private String code;
	private List<User> users;
	private Integer status;
	private String remarks;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="role_id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="role_code", length=50)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@OneToMany(mappedBy = "role", cascade=CascadeType.ALL)
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Column(name="role_status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name="role_remarks", length=255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", code=" + code
				+ ", status=" + status + ", remarks=" + remarks + "]";
	}	
}
