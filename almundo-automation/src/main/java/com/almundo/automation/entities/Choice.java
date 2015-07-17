package com.almundo.automation.entities;

import com.google.gson.annotations.SerializedName;

public class Choice {
	
	private Leg leg;
	
	@SerializedName("id")
	private int  id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Leg getLeg() {
		return leg;
	}

	public void setLeg(Leg leg) {
		this.leg = leg;
	}
	
	

}
