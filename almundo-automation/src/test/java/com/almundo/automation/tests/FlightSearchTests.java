package com.almundo.automation.tests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.almundo.automation.entities.Cluster;
import com.almundo.automation.services.Response;
import com.almundo.automation.utils.DataProviders;

public class FlightSearchTests extends BaseTest {

	private static final String PROPERTY_NAME = "search.properties";
	private static final String TEST_ONE = "test1";
	private static final String TEST_TWO = "test2";

	@Test(groups = { "NONE" })
	public void testMethodOne() {
		String data = this.data.getPropertiesValues(TEST_ONE, PROPERTY_NAME);
		this.httpClient.setSearchRequest(data);
		Response response = this.httpClient.post();
		System.out.println(response.getPlainResponse());
		Assert.assertTrue(this.validateFlights(response));
	}

	@Test(groups = { "NONE" })
	public void testMethodTwo() {
		String data = this.data.getPropertiesValues(TEST_TWO, PROPERTY_NAME);
		this.httpClient.setSearchRequest(data);
		Response response = this.httpClient.post();
		System.out.println(response.getPlainResponse());
		Assert.assertTrue(this.validateClusters(response));
	}

	@Test(groups = { "NONE" }, dataProvider = "test1", dataProviderClass = DataProviders.class)
	public void verifyPricesAccordingToNumberOfPax() {
		String data = this.data.getPropertiesValues(TEST_ONE, PROPERTY_NAME);
		this.httpClient.setSearchRequest(data);
		Response response = this.httpClient.post();
		List<Cluster> clusters = response.getSearchFlightsAndClusters()
				.getClusters();
		Map<String, Object> results = verifyPrices(clusters);
		String carrier = (String) results.get("Carrier");
		boolean result = (Boolean) results.get("Result");
		String expPrice = (String) results.get("ExpectedPrice");
		String actualPrice = (String) results.get("ActualPrice");
		Assert.assertTrue(result, "The carrier " + carrier + " has "
				+ actualPrice + " meanwhile the expected price is " + expPrice);

	}

	@Test(groups = { "flight-search" }, dataProvider = "test1", dataProviderClass = DataProviders.class)
	public void holaKase(String param1, String param2, String param3,
			String param4, String param5, String param6) {
		System.out.println("");
		System.out.println(" param1 "+param1 + " param2 " + param2 + " param3 " + param3 + " param4 " + param4 + " param5 " + param5 + " param6 " + param6 + "");
		System.out.println("");
	}

	private Map<String, Object> verifyPrices(List<Cluster> clusters) {
		Map<String, Object> result = new HashMap<String, Object>();
		for (Cluster cluster : clusters) {
			float adultPrice = cluster.getPrice().getDetail().getAdultPrice();
			float childPrice = cluster.getPrice().getDetail().getChildPrice();
			float infantPrice = cluster.getPrice().getDetail().getInfantPrice();
			float taxPrice = cluster.getPrice().getDetail().getTaxes();
			float extraTax = cluster.getPrice().getDetail().getExtra_tax();
			float total = cluster.getPrice().getTotal();
			float sum = (adultPrice * 2) + childPrice + infantPrice + taxPrice
					+ extraTax;
			if (total != sum) {
				result.put("Carrier", cluster.getValidating_carrier());
				result.put("Result", Boolean.FALSE);
				result.put("ExpectedPrice", Float.toString(total));
				result.put("ActualPrice", Float.toString(sum));
				return result;
			}
		}
		result.put("Result", Boolean.TRUE);
		result.put("Carrier", null);
		return result;
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
