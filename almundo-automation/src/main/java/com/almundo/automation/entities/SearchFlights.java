package com.almundo.automation.entities;

import java.io.Serializable;
import java.util.List;

/**
 * Class that represent the response of search flights using Search service
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
	private List<Cluster> clusters;
	private List<Filter> filters;

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

	public List<Cluster> getClusters() {
		return clusters;
	}

	public void setClusters(List<Cluster> clusters) {
		this.clusters = clusters;
	}

	public List<Filter> getFilters() {
		return filters;
	}

	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}

}
