package com.almundo.automation.services;

import java.lang.reflect.Type;
import java.util.List;

import com.almundo.automation.deserializer.ClusterDeserealizer;
import com.almundo.automation.deserializer.FilterDeserealizer;
import com.almundo.automation.deserializer.SearchFlightsDeserealizer;
import com.almundo.automation.deserializer.SegmentDeserializer;
import com.almundo.automation.entities.Cluster;
import com.almundo.automation.entities.Filter;
import com.almundo.automation.entities.SearchFlights;
import com.almundo.automation.entities.Segment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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

	/**
	 * Get the the response of search flights using Search service
	 * 
	 * @return SearchFlights
	 */
	public SearchFlights getSearchFlightsAndClusters() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(SearchFlights.class,
				new SearchFlightsDeserealizer());
		Gson gson = gsonBuilder.create();

		SearchFlights searchFlights = gson.fromJson(this.plainResponse,
				SearchFlights.class);

		final Type clusterA = new TypeToken<Cluster>() {
		}.getType();
		gsonBuilder.registerTypeAdapter(clusterA, new ClusterDeserealizer());
		gson = gsonBuilder.create();
		@SuppressWarnings("unchecked")
		List<Cluster> clusters = (List<Cluster>) gson.fromJson(
				this.plainResponse, clusterA);
		searchFlights.setClusters(clusters);
		return searchFlights;
	}

	/**
	 * 
	 * @return List<Filters>
	 */
	public List<Filter> getFilters() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(SearchFlights.class,
				new SearchFlightsDeserealizer());
		Gson gson = gsonBuilder.create();

		final Type filtersType = new TypeToken<Filter>() {
		}.getType();
		gsonBuilder.registerTypeAdapter(filtersType, new FilterDeserealizer());
		gson = gsonBuilder.create();
		@SuppressWarnings("unchecked")
		List<Filter> filters = (List<Filter>) gson.fromJson(this.plainResponse,
				filtersType);
		return filters;
	}

	/**
	 * 
	 * @return Segment
	 */
	public Segment getSegments() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Segment.class,
				new SegmentDeserializer());
		Gson gson = gsonBuilder.create();
		Segment segment = gson.fromJson(this.plainResponse, Segment.class);
		return segment;
	}
}
