package com.almundo.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.almundo.POJO.SearchFlights;
import com.almundo.automation.Deserializer.SearchFlightsDeserealizer;
import com.almundo.automation.services.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * @author zenen.morales
 *
 */
public class SearchResponse extends BaseTest {

	private String glbUserName = "glb-amascheroni";

	@Test(groups = { "include-test-one" })
	public void testMethodOne() {
		this.httpClient.setRequest();
		this.httpClient.openConection();
		this.httpClient.addHeaderValue("X-ApiKey", "5592f8fd99325b40cba48649");
		this.httpClient.addHeaderValue("X-UOW", this.glbUserName);
		Response response = this.httpClient.post();
		System.out.println();
		Assert.assertTrue(this.search(response.getPlainResponse()));
	}

	private boolean search(String jsonResponse) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(SearchFlights.class,
				new SearchFlightsDeserealizer());
		Gson gson = gsonBuilder.create();

		SearchFlights searchResponse = gson.fromJson(
				jsonResponse, SearchFlights.class);
//		SearchFlights searchResponse = gson.fromJson(jsonResponse,
//				SearchFlights.class);
//		searchResponse.setLowestPricesByAirline(lowestPricesByAirline);
		if (!searchResponse.getLowestPricesByAirline().getAirlines().isEmpty())
			return true;
		else
			return false;
	}

}
