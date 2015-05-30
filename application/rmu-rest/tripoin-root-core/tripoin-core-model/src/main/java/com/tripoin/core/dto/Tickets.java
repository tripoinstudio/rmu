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
@XmlType(name = "", propOrder = { "master_ticket", "response_code", "response_msg", "result" })
@XmlRootElement(name = "Tickets")
public class Tickets {
	@XmlElement(name = "Ticket", required = true)
	private List<TicketDTO> master_ticket;
	
	@XmlElement(name = "response_code", required = true)
	private String response_code;
	
	@XmlElement(name = "response_msg", required = true)
	private String response_msg;
	
	@XmlElement(name = "result", required = true)
	private String result;

	public List<TicketDTO> getMaster_ticket() {
		return master_ticket;
	}

	public void setMaster_ticket(List<TicketDTO> master_ticket) {
		this.master_ticket = master_ticket;
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
		return "Tickets [master_ticket=" + master_ticket + ", response_code="
				+ response_code + ", response_msg=" + response_msg
				+ ", result=" + result + "]";
	}
	
}
