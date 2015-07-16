package com.almundo.automation.tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.almundo.automation.entities.Airline;
import com.almundo.automation.entities.Cluster;
import com.almundo.automation.services.Response;
import com.almundo.automation.utils.DataProviders;
import com.almundo.automation.utils.Utils;

public class FlightSearchTests extends BaseTest {

	
	@Test(description = "Test that verifies if the airlines names are not null",
		  groups = { "flight-search" },
		  dataProvider = "test1", dataProviderClass = DataProviders.class)
	public void verifyAirlineNamesNotNull(Map<String, String> data) {
		Utils util = new Utils();
		String reqDate = 
				util.convertToSpecifDate(data.get("date"));
		data.remove("date");
		data.put("departure", reqDate);
		
		this.httpClient.setSearchRequest(data);
		Response response = this.httpClient.post();
		
		List<Airline> airlines = response.getSearchFlights()
				.getLowestPricesByAirline().getAirlines();
		
		for(Airline airline: airlines) {
			if(airline.getName().toUpperCase().equals("NULL")){
				Assert.fail("The airline " + airline.getCode()+ " with "
						+ "catalog ID " + airline.getCatalog_id() + " has "
						+ "a null value instead of its name");
			}
		}
	}
	
	
	@Test(description = "Test that verifies that given a number of passengers, "
			+ "the total amount be equals to the detailed amount per passenger",
		  groups = { "flight-search" },
		  dataProvider = "test1", dataProviderClass = DataProviders.class)
	public void verifyPricesAcordingToNumberOfPax(Map<String, String> data) {
		Utils util = new Utils();
		String reqDate = 
				util.convertToSpecifDate(data.get("date"));
		data.remove("date");
		data.put("departure", reqDate);
		
		this.httpClient.setSearchRequest(data);
		Response response = this.httpClient.post();
		
		List<Cluster> clusters = response.getSearchFlightsAndClusters().getClusters();
		
		for (Cluster cluster : clusters) {
			float adultPrice = cluster.getPrice().getDetail().getAdultPrice();
			float childPrice = cluster.getPrice().getDetail().getChildPrice();
			float infantPrice = cluster.getPrice().getDetail().getInfantPrice();
			float taxPrice = cluster.getPrice().getDetail().getTaxes();
			float extraTax = cluster.getPrice().getDetail().getExtra_tax();
			float total = cluster.getPrice().getTotal();
			float sum = (adultPrice * Float.parseFloat(data.get("adults"))) + 
						(childPrice * Float.parseFloat(data.get("children"))) +
						(infantPrice * Float.parseFloat(data.get("infants"))) +
						taxPrice + extraTax;
			if (total != sum) {
				Assert.fail("The carrier "+ cluster.getValidating_carrier()
						+ " should have an amount of " + total
						+ ", but it has " + sum);
			}
		}
	}

}