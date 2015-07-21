package com.almundo.automation.services;

import java.lang.reflect.Type;
import java.util.List;

import com.almundo.automation.deserializer.ChoiceDeserializer;
import com.almundo.automation.deserializer.ClusterDeserealizer;
import com.almundo.automation.deserializer.FilterDeserealizer;
import com.almundo.automation.deserializer.LegDeserializer;
import com.almundo.automation.deserializer.OperatingCarrierDeserealizer;
import com.almundo.automation.deserializer.SearchFlightsDeserealizer;
import com.almundo.automation.deserializer.SegmentDeserializer;
import com.almundo.automation.entities.Choice;
import com.almundo.automation.entities.Cluster;
import com.almundo.automation.entities.Filter;
import com.almundo.automation.entities.Leg;
import com.almundo.automation.entities.OperatingCarrier;
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
	public List<Segment> getSegments() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Segment.class,
				new SegmentDeserializer());
		Gson gson = gsonBuilder.create();
		final Type segmentsType = new TypeToken<Segment>() {
		}.getType();
		gson = gsonBuilder.create();
		@SuppressWarnings("unchecked")
		List<Segment> segments = (List<Segment>) gson.fromJson(
				this.plainResponse, segmentsType);
		return segments;
	}

	/**
	 * 
	 * @return OperatingCarrier
	 */
	public List<OperatingCarrier> getOperatingCarrier() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(OperatingCarrier.class,
				new OperatingCarrierDeserealizer());
		Gson gson = gsonBuilder.create();
		final Type operatingCarrierType = new TypeToken<OperatingCarrier>() {
		}.getType();
		gson = gsonBuilder.create();
		@SuppressWarnings("unchecked")
		List<OperatingCarrier> operatingCarrier = (List<OperatingCarrier>) gson
				.fromJson(this.plainResponse, operatingCarrierType);
		return operatingCarrier;
	}

	public Choice getChoice() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Choice.class, new ChoiceDeserializer());
		Gson gson = gsonBuilder.create();
		Choice choice = gson.fromJson(this.plainResponse, Choice.class);
		return choice;

	}

	public Leg getLeg() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Leg.class, new LegDeserializer());
		Gson gson = gsonBuilder.create();
		Leg leg = gson.fromJson(this.plainResponse, Leg.class);
		return leg;

	}
}
