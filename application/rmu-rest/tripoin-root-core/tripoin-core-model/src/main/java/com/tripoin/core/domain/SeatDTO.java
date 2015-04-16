package com.tripoin.core.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "id", "code", "no", "status", "remarks" })
@XmlRootElement(name = "Seat")
public class SeatDTO {
	private Integer seat_id;
	private String seat_code;
	private String seat_no;
	private Integer seat_status;
	private String seat_remarks;

	public SeatDTO() {}
	
	public SeatDTO(Integer seat_id, String seat_code, String seat_no,
			Integer seat_status, String seat_remarks) {
		super();
		this.seat_id = seat_id;
		this.seat_code = seat_code;
		this.seat_no = seat_no;
		this.seat_status = seat_status;
		this.seat_remarks = seat_remarks;
	}

	public Integer getSeat_id() {
		return seat_id;
	}

	public void setSeat_id(Integer seat_id) {
		this.seat_id = seat_id;
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

	public Integer getSeat_status() {
		return seat_status;
	}

	public void setSeat_status(Integer seat_status) {
		this.seat_status = seat_status;
	}

	public String getSeat_remarks() {
		return seat_remarks;
	}

	public void setSeat_remarks(String seat_remarks) {
		this.seat_remarks = seat_remarks;
	}

	@Override
	public String toString() {
		return "SeatDTO [seat_id=" + seat_id + ", seat_code=" + seat_code
				+ ", seat_no=" + seat_no + ", seat_status=" + seat_status
				+ ", seat_remarks=" + seat_remarks + "]";
	}
}
