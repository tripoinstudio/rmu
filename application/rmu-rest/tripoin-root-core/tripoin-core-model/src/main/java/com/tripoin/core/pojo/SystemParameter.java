package com.tripoin.core.pojo;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class SystemParameter {

	private Long id;
	private String name;
	private String value;
	private String remarks;	

	public SystemParameter() {}
	
	public SystemParameter(Long id, String name, String value, String remarks) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
		this.remarks = remarks;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "SystemParameter [id=" + id + ", name=" + name + ", value="
				+ value + ", remarks=" + remarks + "]";
	}

}
