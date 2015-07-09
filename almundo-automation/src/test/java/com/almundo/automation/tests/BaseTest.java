package com.almundo.automation.tests;

import com.almundo.automation.services.HTTPClient;


public class BaseTest {

	protected HTTPClient httpClient;
	
	public BaseTest() {
		this.instanceClient();
	}
	
	private void instanceClient(){
		this.httpClient = new HTTPClient();
	}

}
