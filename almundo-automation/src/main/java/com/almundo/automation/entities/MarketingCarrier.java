package com.almundo.automation.entities;

public class MarketingCarrier {
	
	private int catalogId;
	private String code;
	private String name;
	
	public MarketingCarrier(int catalogId, String code, String name) {
		this.catalogId = catalogId;
		this.code = code;
		this.name = name;
	}

	public int getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
