package com.tripoin.core.domain;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "menu_id", "menu_name", "menu_type", "menu_price", "menu_image_url" })
@XmlRootElement(name = "Menu")
public class MenuDTO {
	private Integer menu_id;
	private String menu_name;
	private Integer menu_type;
	private BigDecimal menu_price;
	private String menu_image_url;

	public MenuDTO() {}

	public MenuDTO(Integer menu_id, String menu_name, Integer menu_type,
			BigDecimal menu_price, String menu_image_url) {
		super();
		this.menu_id = menu_id;
		this.menu_name = menu_name;
		this.menu_type = menu_type;
		this.menu_price = menu_price;
		this.menu_image_url = menu_image_url;
	}

	public Integer getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(Integer menu_id) {
		this.menu_id = menu_id;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public Integer getMenu_type() {
		return menu_type;
	}

	public void setMenu_type(Integer menu_type) {
		this.menu_type = menu_type;
	}

	public BigDecimal getMenu_price() {
		return menu_price;
	}

	public void setMenu_price(BigDecimal menu_price) {
		this.menu_price = menu_price;
	}

	public String getMenu_image_url() {
		return menu_image_url;
	}

	public void setMenu_image_url(String menu_image_url) {
		this.menu_image_url = menu_image_url;
	}

	@Override
	public String toString() {
		return "MenuDTO [menu_id=" + menu_id + ", menu_name=" + menu_name
				+ ", menu_type=" + menu_type + ", menu_price=" + menu_price
				+ ", menu_image_url=" + menu_image_url + "]";
	}
}
