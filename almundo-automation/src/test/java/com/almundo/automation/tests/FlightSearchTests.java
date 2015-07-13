package com.almundo.automation.tests;

import org.testng.annotations.Test;

import com.almundo.automation.services.Response;

public class FlightSearchTests extends BaseTest{
	
	private static final String PROPERTY_NAME = "search.properties";
	private static final String TEST_ONE = "test1";
	private static final String TEST_TWO = "test2";

	@Test(groups = { "flight-search" })
	public void testMethodOne() {
		String data = this.data.getPropertiesValues(TEST_ONE, PROPERTY_NAME);
		this.httpClient.setSearchRequest(data);
		Response response = this.httpClient.post();
		System.out.println(response.getPlainResponse());
		
	}
	
	@Test(groups = { "flight-search" })
	public void testMethodTwo() {
		String data = this.data.getPropertiesValues(TEST_TWO, PROPERTY_NAME);
		System.out.println(data);
		
	}
	

}
