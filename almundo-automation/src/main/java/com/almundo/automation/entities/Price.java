package com.almundo.automation.entities;

import com.google.gson.annotations.SerializedName;

/**
 * 
 * @author zenen.morales
 *
 */
public class Price {

	@SerializedName("total")
	private float total;

	private Detail detail;

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public Detail getDetail() {
		return detail;
	}

	public void setDetail(Detail detail) {
		this.detail = detail;
	}

}
