package com.almundo.automation.tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.almundo.automation.services.Response;

public class FlightSearchTests extends BaseTest{
	
	private static final String PROPERTY_NAME = "search.properties";

	@Test(groups = { "flight-search" })
	@Parameters({"testID"})
    public void testMethodOne(String testID) {
		String data = this.data.getPropertiesValues(testID, PROPERTY_NAME);
		this.httpClient.setSearchRequest(data);
		Response response = this.httpClient.post();
		System.out.println(response.getPlainResponse());
		
	}
	
	@Test(groups = { "flight-search" })
	@Parameters({"testID"})
    public void testMethodTwo(String testID) {
		String data = this.data.getPropertiesValues(testID, PROPERTY_NAME);
		System.out.println(data);
		
	}
	

}
