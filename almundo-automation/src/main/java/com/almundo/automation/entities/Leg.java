package com.almundo.automation.entities;

import java.util.List;

public class Leg {
	
	private List<MarketingCarrier> marketingCarriers;

	public Leg(List<MarketingCarrier> marketingCarriers) {
		this.marketingCarriers = marketingCarriers;
	}

	public List<MarketingCarrier> getMarketingCarriers() {
		return marketingCarriers;
	}

	public void setMarketingCarriers(List<MarketingCarrier> marketingCarriers) {
		this.marketingCarriers = marketingCarriers;
	}
	
	

}
