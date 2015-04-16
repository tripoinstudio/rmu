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
@XmlType(name = "", propOrder = { "master_cariage", "response_code", "response_msg", "RESULT" })
@XmlRootElement(name = "Carriages")
public class Carriages {	
	@XmlElement(name = "Carriage", required = true)
	private List<CarriageDTO> master_cariage;
	
	@XmlElement(name = "response_code", required = true)
	private String response_code;
	
	@XmlElement(name = "response_msg", required = true)
	private String response_msg;
	
	@XmlElement(name = "RESULT", required = true)
	private String RESULT;

	public List<CarriageDTO> getMaster_cariage() {
		return master_cariage;
	}

	public void setMaster_cariage(List<CarriageDTO> master_cariage) {
		this.master_cariage = master_cariage;
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

	public void setRESULT(String RESULT) {
		this.RESULT = RESULT;
	}

	@Override
	public String toString() {
		return "Carriages [master_cariage=" + master_cariage
				+ ", response_code=" + response_code + ", response_msg="
				+ response_msg + ", RESULT=" + RESULT + "]";
	}
}
