package com.tripoin.core.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "id", "no", "remarks" })
@XmlRootElement(name = "Seat")
public class SeatDTO {
	private Integer seat_id;
	private String seat_no;
	private String seat_remarks;

	public SeatDTO() {}
	
	public SeatDTO(Integer seat_id, String seat_no, String seat_remarks) {
		super();
		this.seat_id = seat_id;
		this.seat_no = seat_no;
		this.seat_remarks = seat_remarks;
	}

	public Integer getSeat_id() {
		return seat_id;
	}

	public void setSeat_id(Integer seat_id) {
		this.seat_id = seat_id;
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
		return "SeatDTO [seat_id=" + seat_id + ", seat_no=" + seat_no
				+ ", seat_remarks=" + seat_remarks + "]";
	}
}
