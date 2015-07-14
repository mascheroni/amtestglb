package com.almundo.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.almundo.automation.services.Response;

public class FlightSearchTests extends BaseTest {

	private static final String PROPERTY_NAME = "search.properties";
	private static final String TEST_ONE = "test1";
	private static final String TEST_TWO = "test2";

	@Test(groups = { "flight-search" })
	public void testMethodOne() {
		String data = this.data.getPropertiesValues(TEST_ONE, PROPERTY_NAME);
		this.httpClient.setSearchRequest(data);
		Response response = this.httpClient.post();
		System.out.println(response.getPlainResponse());
		Assert.assertTrue(this.validateFlights(response));
	}

	@Test(groups = { "flight-search" })
	public void testMethodTwo() {
		String data = this.data.getPropertiesValues(TEST_TWO, PROPERTY_NAME);
		this.httpClient.setSearchRequest(data);
		Response response = this.httpClient.post();
		System.out.println(response.getPlainResponse());
		Assert.assertTrue(this.validateClusters(response));
	}

	/**
	 * Validate response of Search flights service
	 * 
	 * @param response
	 *            Response
	 * @return boolean
	 */
	private boolean validateFlights(Response response) {
		if (!response.getSearchFlights().getLowestPricesByAirline()
				.getAirlines().isEmpty())
			return true;
		else
			return false;
	}

	/**
	 * Validate response of Search flights service
	 * 
	 * @param response
	 *            Response
	 * @return boolean
	 */
	private boolean validateClusters(Response response) {
		if (!response.getSearchFlightsAndClusters().getClusters().isEmpty())
			return true;
		else
			return false;
	}

}
