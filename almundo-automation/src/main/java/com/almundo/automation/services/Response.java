package com.almundo.automation.services;

import com.almundo.POJO.LowestPricesByAirline;
import com.almundo.POJO.SearchFlights;
import com.almundo.automation.Deserializer.LowestPricesByAirlineDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Response {

	private String plainResponse;

	public Response(String response) {
		this.plainResponse = response;
	}

	public String getPlainResponse() {
		return this.plainResponse;
	}

	/**
	 * Get the the responseof searching flights
	 * @return
	 */
	public SearchFlights getSearchFlights() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(LowestPricesByAirline.class,
				new LowestPricesByAirlineDeserializer());
		Gson gson = gsonBuilder.create();

		LowestPricesByAirline lowestPricesByAirline = gson.fromJson(
				this.plainResponse, LowestPricesByAirline.class);
		SearchFlights searchResponse = gson.fromJson(this.plainResponse,
				SearchFlights.class);
		searchResponse.setLowestPricesByAirline(lowestPricesByAirline);
		return searchResponse;
	}

}
