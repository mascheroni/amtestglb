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
	private List<Clusters> clusters;

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

	public List<Clusters> getClusters() {
		return clusters;
	}

	public void setClusters(List<Clusters> clusters) {
		this.clusters = clusters;
	}

}
