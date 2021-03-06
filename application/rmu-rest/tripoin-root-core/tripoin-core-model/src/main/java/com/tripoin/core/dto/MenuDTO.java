package com.tripoin.core.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "menu_code", "menu_name", "menu_type", "menu_price", "menu_stock", "menu_image_url", "menu_rating", "master_image" })
@XmlRootElement(name = "Menu")
public class MenuDTO {
	private String menu_code;
	private String menu_name;
	private Integer menu_type;
	private BigDecimal menu_price;
	private Integer menu_stock;
	private String menu_image_url;
	private BigDecimal menu_rating;
	private List<ImageDTO> master_image;

	public MenuDTO() {}

	public MenuDTO(String menu_code, String menu_name, Integer menu_type,
			BigDecimal menu_price, Integer menu_stock, String menu_image_url,
			BigDecimal menu_rating, List<ImageDTO> master_image) {
		super();
		this.menu_code = menu_code;
		this.menu_name = menu_name;
		this.menu_type = menu_type;
		this.menu_price = menu_price;
		this.menu_stock = menu_stock;
		this.menu_image_url = menu_image_url;
		this.menu_rating = menu_rating;
		this.master_image = master_image;
	}

	public String getMenu_code() {
		return menu_code;
	}

	public void setMenu_code(String menu_code) {
		this.menu_code = menu_code;
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

	public Integer getMenu_stock() {
		return menu_stock;
	}

	public void setMenu_stock(Integer menu_stock) {
		this.menu_stock = menu_stock;
	}

	public String getMenu_image_url() {
		return menu_image_url;
	}

	public void setMenu_image_url(String menu_image_url) {
		this.menu_image_url = menu_image_url;
	}

	public BigDecimal getMenu_rating() {
		return menu_rating;
	}

	public void setMenu_rating(BigDecimal menu_rating) {
		this.menu_rating = menu_rating;
	}

	public List<ImageDTO> getMaster_image() {
		return master_image;
	}

	public void setMaster_image(List<ImageDTO> master_image) {
		this.master_image = master_image;
	}

	@Override
	public String toString() {
		return "MenuDTO [menu_code=" + menu_code + ", menu_name=" + menu_name
				+ ", menu_type=" + menu_type + ", menu_price=" + menu_price
				+ ", menu_stock=" + menu_stock + ", menu_image_url="
				+ menu_image_url + ", menu_rating=" + menu_rating
				+ ", master_image=" + master_image + "]";
	}

}
