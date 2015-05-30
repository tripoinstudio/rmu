package com.tripoin.core.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "profile_code", "profile_name", "profile_id_card", "profile_email", "profile_address", "profile_citizenship", "profile_image" })
@XmlRootElement(name = "Profile")
public class ProfileDTO {

	private String profile_code;
	private String profile_name;
	private String profile_id_card;
	private String profile_email;
	private String profile_address;
	private String profile_citizenship;
	private String profile_image;

	public ProfileDTO() {}

	public ProfileDTO(String profile_code, String profile_name,
			String profile_id_card, String profile_email,
			String profile_address, String profile_citizenship,
			String profile_image) {
		super();
		this.profile_code = profile_code;
		this.profile_name = profile_name;
		this.profile_id_card = profile_id_card;
		this.profile_email = profile_email;
		this.profile_address = profile_address;
		this.profile_citizenship = profile_citizenship;
		this.profile_image = profile_image;
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

	public String getProfile_id_card() {
		return profile_id_card;
	}

	public void setProfile_id_card(String profile_id_card) {
		this.profile_id_card = profile_id_card;
	}

	public String getProfile_email() {
		return profile_email;
	}

	public void setProfile_email(String profile_email) {
		this.profile_email = profile_email;
	}

	public String getProfile_address() {
		return profile_address;
	}

	public void setProfile_address(String profile_address) {
		this.profile_address = profile_address;
	}

	public String getProfile_citizenship() {
		return profile_citizenship;
	}

	public void setProfile_citizenship(String profile_citizenship) {
		this.profile_citizenship = profile_citizenship;
	}

	public String getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}

	@Override
	public String toString() {
		return "ProfileDTO [profile_code=" + profile_code + ", profile_name="
				+ profile_name + ", profile_id_card=" + profile_id_card
				+ ", profile_email=" + profile_email + ", profile_address="
				+ profile_address + ", profile_citizenship="
				+ profile_citizenship + ", profile_image=" + profile_image
				+ "]";
	}

}
