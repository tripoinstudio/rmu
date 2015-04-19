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
@XmlType(name = "", propOrder = { "master_carriage", "response_code", "response_msg", "result" })
@XmlRootElement(name = "Carriages")
public class Carriages {	
	@XmlElement(name = "Carriage", required = true)
	private List<CarriageDTO> master_carriage;
	
	@XmlElement(name = "response_code", required = true)
	private String response_code;
	
	@XmlElement(name = "response_msg", required = true)
	private String response_msg;
	
	@XmlElement(name = "result", required = true)
	private String result;

	public List<CarriageDTO> getMaster_carriage() {
		return master_carriage;
	}

	public void setMaster_carriage(List<CarriageDTO> master_carriage) {
		this.master_carriage = master_carriage;
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
		return "Carriages [master_carriage=" + master_carriage
				+ ", response_code=" + response_code + ", response_msg="
				+ response_msg + ", result=" + result + "]";
	}
}
