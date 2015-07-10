package com.almundo.automation.tests;

import org.testng.annotations.Test;

import com.almundo.automation.services.Response;

public class FlightSearchTests extends BaseTest{

	@Test(groups = { "include-test-one" })
    public void testMethodOne() {
		this.setParameters();
		this.httpClient.setRequest(parameters);
		this.httpClient.openConection();
		this.httpClient.addHeaderValue("X-ApiKey", "5592f8fd99325b40cba48649");
		this.httpClient.addHeaderValue("X-UOW", this.glbUserName);
		Response response = this.httpClient.post();
		System.out.println(response.getPlainResponse());
		
	}
	

}
