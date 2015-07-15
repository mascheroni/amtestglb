package com.almundo.automation.tests;

import com.almundo.automation.services.HTTPClient;
import com.almundo.automation.utils.DataProviders;
import com.almundo.automation.utils.PropertyReader;


public class BaseTest {

	protected HTTPClient httpClient;
	protected PropertyReader data;
	protected DataProviders providers;
	
	public BaseTest() {
		this.instanceClient();
		this.instancePropertyReader();
		this.instanceDataProviders();
	}
	
	private void instanceClient(){
		this.httpClient = new HTTPClient();
	}
	
	private void instancePropertyReader(){
		this.data = new PropertyReader();
	}
	
	private void instanceDataProviders(){
		this.providers = new DataProviders();
	}

}
