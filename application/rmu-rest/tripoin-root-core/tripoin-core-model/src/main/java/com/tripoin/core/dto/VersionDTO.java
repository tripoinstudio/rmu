package com.tripoin.core.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "version_table", "version_timestamp", "version_remarks" })
@XmlRootElement(name = "Version")
public class VersionDTO {
	private String version_table;
	private String version_timestamp;
	private String version_remarks;

	public VersionDTO() {}

	public VersionDTO(String version_table, String version_timestamp, String version_remarks) {
		super();
		this.version_table = version_table;
		this.version_timestamp = version_timestamp;
		this.version_remarks = version_remarks;
	}

	public String getVersion_table() {
		return version_table;
	}

	public void setVersion_table(String version_table) {
		this.version_table = version_table;
	}

	public String getVersion_timestamp() {
		return version_timestamp;
	}

	public void setVersion_timestamp(String version_timestamp) {
		this.version_timestamp = version_timestamp;
	}	

	public String getVersion_remarks() {
		return version_remarks;
	}

	public void setVersion_remarks(String version_remarks) {
		this.version_remarks = version_remarks;
	}

	@Override
	public String toString() {
		return "VersionDTO [version_table=" + version_table
				+ ", version_timestamp=" + version_timestamp
				+ ", version_remarks=" + version_remarks + "]";
	}
	
}
