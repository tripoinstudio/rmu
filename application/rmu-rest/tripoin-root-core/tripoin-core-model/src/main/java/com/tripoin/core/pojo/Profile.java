package com.tripoin.core.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Entity
@Table(name="master_profile")
public class Profile {
	
	private Integer id;
	private String code;
	private String name;
	private String idCard;
	private String email;
	private String address;
	private String citizenship;
	private String image;
	private User user;
	private Integer status;
	private String remarks;
	private List<Ticket> tickets;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="profile_id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="profile_code", length=50)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name="profile_name", length=150)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="profile_id_card", length=100)
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	@Column(name="profile_email", length=100)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="profile_address", length=250)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name="profile_citizenship", length=100)
	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	@Column(name="profile_image", length=100)
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name="profile_status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name="profile_remarks", length=255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "profile", cascade=CascadeType.ALL)
	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", code=" + code + ", name=" + name
				+ ", idCard=" + idCard + ", email=" + email + ", address="
				+ address + ", citizenship=" + citizenship + ", image=" + image
				+ ", user=" + user + ", status=" + status + ", remarks="
				+ remarks + "]";
	}
	
}
