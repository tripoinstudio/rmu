package com.tripoin.core.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Entity
@Table(name="trx_version_filter")
public class VersionFilter {
	
	private Integer id;
	private User user;
	private Date versionOrderHeader;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="version_filter_id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name="version_filter_order")
	public Date getVersionOrderHeader() {
		return versionOrderHeader;
	}

	public void setVersionOrderHeader(Date versionOrderHeader) {
		this.versionOrderHeader = versionOrderHeader;
	}

	@Override
	public String toString() {
		return "VersionFilter [id=" + id + ", user=" + user
				+ ", versionOrderHeader=" + versionOrderHeader + "]";
	}
	
}
