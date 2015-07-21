package com.almundo.automation.entities;

import java.util.List;


public class Choice {
	private String id;
	private List<Leg> leg;
	

	public List<Leg> getLeg() {
		return leg;
	}

	public void setLeg(List<Leg> leg) {
		this.leg = leg;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	

}
