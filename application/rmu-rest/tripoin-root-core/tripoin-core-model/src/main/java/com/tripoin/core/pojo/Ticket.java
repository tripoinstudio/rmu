package com.tripoin.core.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Entity
@Table(name="master_ticket")
public class Ticket {

	private Integer id;
	private String ticketNo;
	private Date timeArrive;
	private Date timeDeparture;
	private Profile profile;
	private Train train;
	private Carriage carriage;
	private Seat seat;
	private Integer status;
	private String remarks;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ticket_id")
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="ticket_no", length=100)
	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	@Column(name="ticket_time_arrive")
	public Date getTimeArrive() {
		return timeArrive;
	}

	public void setTimeArrive(Date timeArrive) {
		this.timeArrive = timeArrive;
	}

	@Column(name="ticket_time_departure")
	public Date getTimeDeparture() {
		return timeDeparture;
	}

	public void setTimeDeparture(Date timeDeparture) {
		this.timeDeparture = timeDeparture;
	}

	@ManyToOne
	@JoinColumn(name = "profile_id", nullable = false)
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@ManyToOne
	@JoinColumn(name = "train_id", nullable = false)
	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	@ManyToOne
	@JoinColumn(name = "carriage_id", nullable = false)
	public Carriage getCarriage() {
		return carriage;
	}

	public void setCarriage(Carriage carriage) {
		this.carriage = carriage;
	}

	@ManyToOne
	@JoinColumn(name = "seat_id", nullable = false)
	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	@Column(name="ticket_status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name="ticket_remarks", length=255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", ticketNo=" + ticketNo + ", timeArrive="
				+ timeArrive + ", timeDeparture=" + timeDeparture
				+ ", profile=" + profile + ", train=" + train + ", carriage="
				+ carriage + ", seat=" + seat + ", status=" + status
				+ ", remarks=" + remarks + "]";
	}
	
}
