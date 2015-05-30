package com.tripoin.core.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Entity
@Table(name="master_version")
public class Version {
	
	private Integer id;
	private String table;
	private Date timestamp;
	private Integer status;
	private String remarks;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="version_id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="version_table", length=255)
	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	@Column(name="version_timestamp")
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Column(name="version_status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name="version_remarks", length=255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "Version [id=" + id + ", table=" + table + ", timestamp="
				+ timestamp + ", status=" + status + ", remarks=" + remarks
				+ "]";
	}	
}
