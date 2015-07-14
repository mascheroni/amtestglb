package com.almundo.automation.entities;

import com.google.gson.annotations.SerializedName;

/**
 * 
 * @author zenen.morales
 *
 */
public class Detail {

	@SerializedName("adults")
	private float adults;

	@SerializedName("children")
	private float children;

	@SerializedName("infants")
	private float infants;

	@SerializedName("taxes")
	private float taxes;

	@SerializedName("extra_tax")
	private float extra_tax;

	public float getAdults() {
		return adults;
	}

	public void setAdults(float adults) {
		this.adults = adults;
	}

	public float getChildren() {
		return children;
	}

	public void setChildren(float children) {
		this.children = children;
	}

	public float getInfants() {
		return infants;
	}

	public void setInfants(float infants) {
		this.infants = infants;
	}

	public float getTaxes() {
		return taxes;
	}

	public void setTaxes(float taxes) {
		this.taxes = taxes;
	}

	public float getExtra_tax() {
		return extra_tax;
	}

	public void setExtra_tax(float extra_tax) {
		this.extra_tax = extra_tax;
	}

}
