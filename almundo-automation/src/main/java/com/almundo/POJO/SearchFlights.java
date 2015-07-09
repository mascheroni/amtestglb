package com.almundo.POJO;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/**
 * Class that represent the response of search flights
 * 
 * @author zenen.morales
 *
 */
public class SearchFlights implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private LowestPricesByAirline lowestPricesByAirline;

	public SearchFlights() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LowestPricesByAirline getLowestPricesByAirline() {
		return lowestPricesByAirline;
	}

	public void setLowestPricesByAirline(
			LowestPricesByAirline lowestPricesByAirline) {
		this.lowestPricesByAirline = lowestPricesByAirline;
	}

}
