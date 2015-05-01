package com.tripoin.core.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "carriage_code", "carriage_no", "carriage_remarks" })
@XmlRootElement(name = "Carriage")
public class CarriageDTO {
	private String carriage_code;
	private String carriage_no;
	private String carriage_remarks;

	public CarriageDTO() {}

	public CarriageDTO(String carriage_code, String carriage_no,
			String carriage_remarks) {
		super();
		this.carriage_code = carriage_code;
		this.carriage_no = carriage_no;
		this.carriage_remarks = carriage_remarks;
	}

	public String getCarriage_code() {
		return carriage_code;
	}

	public void setCarriage_code(String carriage_code) {
		this.carriage_code = carriage_code;
	}

	public String getCarriage_no() {
		return carriage_no;
	}

	public void setCarriage_no(String carriage_no) {
		this.carriage_no = carriage_no;
	}

	public String getCarriage_remarks() {
		return carriage_remarks;
	}

	public void setCarriage_remarks(String carriage_remarks) {
		this.carriage_remarks = carriage_remarks;
	}

	@Override
	public String toString() {
		return "CarriageDTO [carriage_code=" + carriage_code + ", carriage_no="
				+ carriage_no + ", carriage_remarks=" + carriage_remarks + "]";
	}
}
