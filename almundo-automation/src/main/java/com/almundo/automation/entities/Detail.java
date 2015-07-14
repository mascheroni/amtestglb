package com.almundo.automation.entities;

import com.google.gson.annotations.SerializedName;

/**
 * 
 * @author zenen.morales
 *
 */
public class Detail {

	@SerializedName("adults")
	private int adults;

	@SerializedName("children")
	private int children;

	@SerializedName("infants")
	private int infants;

	@SerializedName("taxes")
	private float taxes;

	@SerializedName("extra_tax")
	private float extra_tax;

	public int getAdults() {
		return adults;
	}

	public void setAdults(int adults) {
		this.adults = adults;
	}

	public int getChildren() {
		return children;
	}

	public void setChildren(int children) {
		this.children = children;
	}

	public int getInfants() {
		return infants;
	}

	public void setInfants(int infants) {
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
