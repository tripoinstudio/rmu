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
@XmlType(name = "", propOrder = { "master_version", "response_code", "response_msg", "result" })
@XmlRootElement(name = "Versions")
public class Versions {			
	@XmlElement(name = "Version", required = true)
	private List<VersionDTO> master_version;
	
	@XmlElement(name = "response_code", required = true)
	private String response_code;
	
	@XmlElement(name = "response_msg", required = true)
	private String response_msg;
	
	@XmlElement(name = "result", required = true)
	private String result;

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
		return "Versions [master_version=" + master_version
				+ ", response_code=" + response_code + ", response_msg="
				+ response_msg + ", result=" + result + "]";
	}

}
