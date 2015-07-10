package com.almundo.automation.services;

import com.almundo.automation.deserializer.SearchFlightsDeserealizer;
import com.almundo.automation.entities.SearchFlights;
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
	 * Get the the response of search flights using Search service
	 * 
	 * @return SearchFlights
	 */
	public SearchFlights getSearchFlights() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(SearchFlights.class,
				new SearchFlightsDeserealizer());
		Gson gson = gsonBuilder.create();

		SearchFlights searchFlights = gson.fromJson(this.plainResponse,
				SearchFlights.class);
		return searchFlights;
	}

}
