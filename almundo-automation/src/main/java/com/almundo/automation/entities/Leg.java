package com.almundo.automation.entities;

import com.google.gson.annotations.SerializedName;

public class Leg {

	private MarketingCarrier marketingCarriers;
	private OperatingCarrier operatingCarrier;
	

	private int number;
	@SerializedName("cabin_type")
	private String cabinType;

	public Leg() {

	}

	public MarketingCarrier getMarketingCarriers() {
		return marketingCarriers;
	}

	public void setMarketingCarriers(MarketingCarrier marketingCarriers) {
		this.marketingCarriers = marketingCarriers;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public OperatingCarrier getOperatingCarrier() {
		return operatingCarrier;
	}

	public void setOperatingCarrier(OperatingCarrier operatingCarrier) {
		this.operatingCarrier = operatingCarrier;
	}

	public String getCabinType() {
		return cabinType;
	}

	public void setCabinType(String cabinType) {
		this.cabinType = cabinType;
	}

}
