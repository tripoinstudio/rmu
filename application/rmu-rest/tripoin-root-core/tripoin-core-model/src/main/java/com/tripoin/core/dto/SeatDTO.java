package com.tripoin.core.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "seat_code", "seat_no", "seat_remarks" })
@XmlRootElement(name = "Seat")
public class SeatDTO {
	private String seat_code;
	private String seat_no;
	private String seat_remarks;

	public SeatDTO() {}

	public SeatDTO(String seat_code, String seat_no, String seat_remarks) {
		super();
		this.seat_code = seat_code;
		this.seat_no = seat_no;
		this.seat_remarks = seat_remarks;
	}

	public String getSeat_code() {
		return seat_code;
	}

	public void setSeat_code(String seat_code) {
		this.seat_code = seat_code;
	}

	public String getSeat_no() {
		return seat_no;
	}

	public void setSeat_no(String seat_no) {
		this.seat_no = seat_no;
	}

	public String getSeat_remarks() {
		return seat_remarks;
	}

	public void setSeat_remarks(String seat_remarks) {
		this.seat_remarks = seat_remarks;
	}

	@Override
	public String toString() {
		return "SeatDTO [seat_code=" + seat_code + ", seat_no=" + seat_no
				+ ", seat_remarks=" + seat_remarks + "]";
	}	
}
