package com.tripoin.core.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "image_code", "image_name", "image_status" })
@XmlRootElement(name = "Image")
public class ImageDTO {
	private String image_code;
	private String image_name;
	private Integer image_status;

	public ImageDTO() {}

	public ImageDTO(String image_code, String image_name, Integer image_status) {
		super();
		this.image_code = image_code;
		this.image_name = image_name;
		this.image_status = image_status;
	}

	public String getImage_code() {
		return image_code;
	}

	public void setImage_code(String image_code) {
		this.image_code = image_code;
	}

	public String getImage_name() {
		return image_name;
	}

	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}

	public Integer getImage_status() {
		return image_status;
	}

	public void setImage_status(Integer image_status) {
		this.image_status = image_status;
	}

	@Override
	public String toString() {
		return "ImageDTO [image_code=" + image_code + ", image_name="
				+ image_name + ", image_status=" + image_status + "]";
	}

}
