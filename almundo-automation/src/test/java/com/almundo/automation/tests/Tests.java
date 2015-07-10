package com.almundo.automation.tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.almundo.automation.services.Parameters;
import com.almundo.automation.services.Response;

public class Tests extends BaseTest {
	
		private String glbUserName = "glb-amascheroni";
		private List<Parameters> parameters = new ArrayList<Parameters>();
		
		public void setParameters(){
			Parameters.FROM.valor("BUE");
			Parameters.TO.valor("RIO");
			Parameters.DEPARTURE.valor("2015-09-10");
			Parameters.SITE.valor("ARG");
			Parameters.LANGUAGE.valor("ES");
			Parameters.ROUNDTRIP.valor("true");
			parameters.add(Parameters.FROM);
			parameters.add(Parameters.TO);
			parameters.add(Parameters.DEPARTURE);
			parameters.add(Parameters.SITE);
			parameters.add(Parameters.LANGUAGE);
			parameters.add(Parameters.ROUNDTRIP);
			
		}
		
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
