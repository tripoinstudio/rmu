package com.tripoin.core.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "ticket_no", "ticket_time_arrive", "time_departure", "profile_code", "profile_name", "train_code", "train_name", "carriage_code", "carriage_name", "seat_code", "seat_name" })
@XmlRootElement(name = "Profile")
public class TicketDTO {

	private String ticket_no;
	private String ticket_time_arrive;
	private String time_departure;
	private String profile_code;
	private String profile_name;
	private String train_code;
	private String train_name;
	private String carriage_code;
	private String carriage_name;
	private String seat_code;
	private String seat_name;

	public TicketDTO() {}

	public TicketDTO(String ticket_no, String ticket_time_arrive,
			String time_departure, String profile_code, String profile_name,
			String train_code, String train_name, String carriage_code,
			String carriage_name, String seat_code, String seat_name) {
		super();
		this.ticket_no = ticket_no;
		this.ticket_time_arrive = ticket_time_arrive;
		this.time_departure = time_departure;
		this.profile_code = profile_code;
		this.profile_name = profile_name;
		this.train_code = train_code;
		this.train_name = train_name;
		this.carriage_code = carriage_code;
		this.carriage_name = carriage_name;
		this.seat_code = seat_code;
		this.seat_name = seat_name;
	}

	public String getTicket_no() {
		return ticket_no;
	}

	public void setTicket_no(String ticket_no) {
		this.ticket_no = ticket_no;
	}

	public String getTicket_time_arrive() {
		return ticket_time_arrive;
	}

	public void setTicket_time_arrive(String ticket_time_arrive) {
		this.ticket_time_arrive = ticket_time_arrive;
	}

	public String getTime_departure() {
		return time_departure;
	}

	public void setTime_departure(String time_departure) {
		this.time_departure = time_departure;
	}

	public String getProfile_code() {
		return profile_code;
	}

	public void setProfile_code(String profile_code) {
		this.profile_code = profile_code;
	}

	public String getProfile_name() {
		return profile_name;
	}

	public void setProfile_name(String profile_name) {
		this.profile_name = profile_name;
	}

	public String getTrain_code() {
		return train_code;
	}

	public void setTrain_code(String train_code) {
		this.train_code = train_code;
	}

	public String getTrain_name() {
		return train_name;
	}

	public void setTrain_name(String train_name) {
		this.train_name = train_name;
	}

	public String getCarriage_code() {
		return carriage_code;
	}

	public void setCarriage_code(String carriage_code) {
		this.carriage_code = carriage_code;
	}

	public String getCarriage_name() {
		return carriage_name;
	}

	public void setCarriage_name(String carriage_name) {
		this.carriage_name = carriage_name;
	}

	public String getSeat_code() {
		return seat_code;
	}

	public void setSeat_code(String seat_code) {
		this.seat_code = seat_code;
	}

	public String getSeat_name() {
		return seat_name;
	}

	public void setSeat_name(String seat_name) {
		this.seat_name = seat_name;
	}

	@Override
	public String toString() {
		return "TicketDTO [ticket_no=" + ticket_no + ", ticket_time_arrive="
				+ ticket_time_arrive + ", time_departure=" + time_departure
				+ ", profile_code=" + profile_code + ", profile_name="
				+ profile_name + ", train_code=" + train_code + ", train_name="
				+ train_name + ", carriage_code=" + carriage_code
				+ ", carriage_name=" + carriage_name + ", seat_code="
				+ seat_code + ", seat_name=" + seat_name + "]";
	}

}
