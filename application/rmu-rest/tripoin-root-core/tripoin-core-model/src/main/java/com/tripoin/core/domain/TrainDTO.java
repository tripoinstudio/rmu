package com.tripoin.core.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "train_id", "train_no", "train_remarks" })
@XmlRootElement(name = "Train")
public class TrainDTO {
	private Integer train_id;
	private String train_no;
	private String train_remarks;

	public TrainDTO() {}
	
	public TrainDTO(Integer train_id, String train_no, String train_remarks) {
		super();
		this.train_id = train_id;
		this.train_no = train_no;
		this.train_remarks = train_remarks;
	}

	public Integer getTrain_id() {
		return train_id;
	}

	public void setTrain_id(Integer train_id) {
		this.train_id = train_id;
	}

	public String getTrain_no() {
		return train_no;
	}

	public void setTrain_no(String train_no) {
		this.train_no = train_no;
	}

	public String getTrain_remarks() {
		return train_remarks;
	}

	public void setTrain_remarks(String train_remarks) {
		this.train_remarks = train_remarks;
	}

	@Override
	public String toString() {
		return "TrainDTO [train_id=" + train_id + ", train_no=" + train_no
				+ ", train_remarks=" + train_remarks + "]";
	}
}
