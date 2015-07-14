package com.almundo.automation.tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.almundo.automation.services.Parameters;
import com.almundo.automation.services.Response;

/**
 * This class use the search service
 * 
 * @author zenen.morales
 *
 */
public class SearchResponseTest extends BaseTest {

	private String glbUserName = "glb-amascheroni";
	private List<Parameters> parameters = new ArrayList<Parameters>();

	@BeforeMethod
	public void setParameters() {
		Parameters.FROM.valor("BUE");
		Parameters.TO.valor("RIO");
		Parameters.DEPARTURE.valor("2015-09-10");
		Parameters.SITE.valor("ARG");
		Parameters.LANGUAGE.valor("ES");
		parameters.add(Parameters.FROM);
		parameters.add(Parameters.TO);
		parameters.add(Parameters.DEPARTURE);
		parameters.add(Parameters.SITE);
		parameters.add(Parameters.LANGUAGE);
	}

	@Test(groups = { "include-test-one" })
	public void testMethodOne() {
		this.httpClient.setRequest(parameters);
		this.httpClient.openConection();
		this.httpClient.addHeaderValue("X-ApiKey", "5592f8fd99325b40cba48649");
		this.httpClient.addHeaderValue("X-UOW", this.glbUserName);
		Response response = this.httpClient.post();
		System.out.println();
		Assert.assertTrue(this.validateflights(response));
	}

	/**
	 * Validate response of Search flights service
	 * 
	 * @param response
	 *            Response
	 * @return boolean
	 */
	private boolean validateflights(Response response) {
		if (!response.getSearchFlights().getLowestPricesByAirline()
				.getAirlines().isEmpty())
			return true;
		else
			return false;
	}

}
