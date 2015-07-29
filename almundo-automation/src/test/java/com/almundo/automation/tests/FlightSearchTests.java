package com.almundo.automation.tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.almundo.automation.entities.Airline;
import com.almundo.automation.entities.Choice;
import com.almundo.automation.entities.Cluster;
import com.almundo.automation.entities.Filter;
import com.almundo.automation.entities.Leg;
import com.almundo.automation.entities.Value;
import com.almundo.automation.services.Response;
import com.almundo.automation.utils.DataProviders;

public class FlightSearchTests extends BaseTest {

	@Test(description = "Verifies that the airlines names are not null", groups = { "flight-search" }, dataProvider = "test1", dataProviderClass = DataProviders.class)
	public void verifyAirlineNamesNotNull(Map<String, String> data) {
		this.httpClient.setSearchRequest(data);
		Response response = this.httpClient.post();

		List<Airline> airlines = response.getSearchFlights()
				.getLowestPricesByAirline().getAirlines();

		for (Airline airline : airlines) {
			if (airline.getName().toUpperCase().equals("NULL")) {
				Assert.fail("The airline " + airline.getCode() + " with "
						+ "catalog ID " + airline.getCatalog_id() + " has "
						+ "a null value instead of its name");
			}
		}
	}

	@Test(description = "Verifies that given a number of passengers, "
			+ "the total amount is equal to the detailed amount per passenger", groups = { "flight-search" }, dataProvider = "test1", dataProviderClass = DataProviders.class)
	public void verifyPricesAcordingToNumberOfPax(Map<String, String> data) {
		this.httpClient.setSearchRequest(data);
		Response response = this.httpClient.post();

		List<Cluster> clusters = response.getSearchFlightsAndClusters()
				.getClusters();

		for (Cluster cluster : clusters) {
			float adultPrice = Math.round(cluster.getPrice().getDetail()
					.getAdultPrice());
			float childPrice = Math.round(cluster.getPrice().getDetail()
					.getChildPrice());
			float infantPrice = Math.round(cluster.getPrice().getDetail()
					.getInfantPrice());
			float taxPrice = Math.round(cluster.getPrice().getDetail()
					.getTaxes());
			float extraTax = Math.round(cluster.getPrice().getDetail()
					.getExtra_tax());
			float total = Math.round(cluster.getPrice().getTotal());
			float sum = (adultPrice * Float.parseFloat(data.get("adults")))
					+ (childPrice * Float.parseFloat(data.get("children")))
					+ (infantPrice * Float.parseFloat(data.get("infants")))
					+ taxPrice + extraTax;
			if (total != sum) {
				Assert.fail("The carrier " + cluster.getValidating_carrier()
						+ " should have an amount of " + total
						+ ", but it has " + sum);
			}
		}
	}

	@Test(description = "Verifies that given an international itinerary, "
			+ "the domestic field is false", groups = { "flight-search" }, dataProvider = "test1", dataProviderClass = DataProviders.class)
	public void verifyDomesticFieldFalse(Map<String, String> data) {
		this.httpClient.setSearchRequest(data);
		Response response = this.httpClient.post();

		List<Cluster> clusters = response.getSearchFlightsAndClusters()
				.getClusters();

		for (Cluster cluster : clusters) {
			Assert.assertFalse(
					cluster.isDomestic(),
					"The International itinerary operated by "
							+ cluster.getValidating_carrier()
							+ " has the domestic field TRUE");
		}
	}

	@Test(description = "Verify that the aiports on the filter sections are not null", groups = { "flight-search" }, dataProvider = "test1", dataProviderClass = DataProviders.class)
	public void verifyAirportsOnFiltersResponse(Map<String, String> data) {
		this.httpClient.setSearchRequest(data);
		Response response = this.httpClient.post();

		List<Filter> filters = response.getSearchFlights().getFilters();
		List<Value> values;

		for (Filter filter : filters) {
			values = filter.getValues();
			for (Value value : values) {
				if (value.getCode().equals("null"))
					Assert.fail("The code for " + value.getName() + " is null");
				if (value.getName().equals("null"))
					Assert.fail("The name for " + value.getCode() + " is null");
			}
		}
	}

	@Test(description = "Verify the most important airports", groups = { "flight-search" }, dataProvider = "test1", dataProviderClass = DataProviders.class)
	public void verifyAirportsInClusters(Map<String, String> data) {
		this.httpClient.setSearchRequest(data);
		Response response = this.httpClient.post();

		List<Cluster> clusters = response.getSearchFlightsAndClusters()
				.getClusters();

		for (Cluster cluster : clusters) {
			Assert.assertNotNull(cluster.getValidating_carrier(),
					"The are no airlines for this cluster " + cluster);
		}
	}

	@Test(description = "Verify not chargimg extra tax", groups = { "flight-search" }, dataProvider = "test1", dataProviderClass = DataProviders.class)
	public void verifyExtraTax(Map<String, String> data) {
		this.httpClient.setSearchRequest(data);
		Response response = this.httpClient.post();

		List<Cluster> clusters = response.getSearchFlightsAndClusters()
				.getClusters();

		for (Cluster cluster : clusters) {
			if (cluster.isDomestic()) {
				Assert.assertSame(
						cluster.getPrice().getDetail().getExtra_tax(), 0,
						"The flight " + response.getSearchFlights().getId()
								+ " should not charg extra tax "
								+ cluster.getPrice().getDetail().getExtra_tax());
			}
		}
	}

	@Test(description = "Verify right type of cabin", groups = { "flight-search" }, dataProvider = "test1", dataProviderClass = DataProviders.class)
	public void verifyTypeCabin(Map<String, String> data) {
		this.httpClient.setSearchRequest(data);
		Response response = this.httpClient.post();

		List<Cluster> clusters = response.getSearchFlightsAndClusters()
				.getClusters();

		for (Cluster cluster : clusters) {
				for(Choice choice :cluster.getSegment().getChoice()){
					for(Leg leg : choice.getLeg()){
						Assert.assertNotNull(leg.getCabinType(), "The fligth "+ response.getSearchFlights().getId()
						+ " does not have the a cabin");
					}
				}
			
		}
	}
	
	@Test(description = "Verify destination airport name not null", groups = { "flight-search" }, dataProvider = "test1", dataProviderClass = DataProviders.class)
	public void verifyDestinationAirportName(Map<String, String> data) {
		this.httpClient.setSearchRequest(data);
		Response response = this.httpClient.post();

		List<Cluster> clusters = response.getSearchFlightsAndClusters()
				.getClusters();

		for (Cluster cluster : clusters) {
				for(Choice choice :cluster.getSegment().getChoice()){
					 Assert.assertNotNull(choice.getDestination().getName(), 
							 "The destination airport is null for "+response.getSearchFlightsAndClusters().getId());
					 
					}
				}
			
		}
	
	@Test(description = "Verify origin airport name not null", groups = { "flight-search" }, dataProvider = "test1", dataProviderClass = DataProviders.class)
	public void verifyOriginAirportName(Map<String, String> data) {
		this.httpClient.setSearchRequest(data);
		Response response = this.httpClient.post();

		List<Cluster> clusters = response.getSearchFlightsAndClusters()
				.getClusters();

		for (Cluster cluster : clusters) {
				for(Choice choice :cluster.getSegment().getChoice()){
					 Assert.assertNotNull(choice.getOrigin().getName(), 
							 "The origin airport is null for "+response.getSearchFlightsAndClusters().getId());
					 
					}
				}
			
		}
	
	@Test(description = "Verify currency code", groups = { "flight-search" }, dataProvider = "test1", dataProviderClass = DataProviders.class)
	public void verifyCurrencyCode(Map<String, String> data) {
		this.httpClient.setSearchRequest(data);
		Response response = this.httpClient.post();

		List<Cluster> clusters = response.getSearchFlightsAndClusters()
				.getClusters();

		for (Cluster cluster : clusters) {
				Assert.assertNotNull(cluster.getPrice().getCurrency().getCode(), 
						"The currency code is null for "
						+response.getSearchFlightsAndClusters().getId());
				}
			
		}
	
	@Test(description = "Verify currency mask", groups = { "flight-search" }, dataProvider = "test1", dataProviderClass = DataProviders.class)
	public void verifyCurrencyMask(Map<String, String> data) {
		this.httpClient.setSearchRequest(data);
		Response response = this.httpClient.post();

		List<Cluster> clusters = response.getSearchFlightsAndClusters()
				.getClusters();

		for (Cluster cluster : clusters) {
				Assert.assertNotNull(cluster.getPrice().getCurrency().getMask(), 
						"The currency mask is null for "
						+response.getSearchFlightsAndClusters().getId());
				}
			
		}
}