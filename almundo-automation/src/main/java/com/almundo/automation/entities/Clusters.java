package com.almundo.automation.entities;

import com.google.gson.annotations.SerializedName;

/**
 * 
 * @author zenen.morales
 *
 */
public class Clusters {

	private Segments segments;
	private Price price;

	@SerializedName("domestic")
	private boolean domestic;

	@SerializedName("validating_carrier")
	private String validating_carrier;

	public Segments getSegments() {
		return segments;
	}

	public void setSegments(Segments segments) {
		this.segments = segments;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public boolean isDomestic() {
		return domestic;
	}

	public void setDomestic(boolean domestic) {
		this.domestic = domestic;
	}

	public String getValidating_carrier() {
		return validating_carrier;
	}

	public void setValidating_carrier(String validating_carrier) {
		this.validating_carrier = validating_carrier;
	}

}
