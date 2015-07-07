package com.almundo.services;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.almundo.JsonObjects.SearchFlights;

/**
 * 
 * @author zenen.morales
 *
 */
public class SearchGetTest {

	private final String X_ApiKey = "5592f8fd99325b40cba48649";
	private final String X_UOW = "gbl-zenen";
	private final String URI = "https://apist.almundo.com/api/flights/itineraries?from=BUE,RIO&to=RIO,BUE&departure=2015-09-10,2015-09-17&site=ARG&language=ES";

	@Test(groups = { "include-test-one" })
	public void testSearchGet() throws Exception {
		ClientRequest request = new ClientRequest(URI);
		request.accept("application/json");
		request.header("X-ApiKey", X_ApiKey);
		request.header("X-UOW", X_UOW);
		Assert.assertTrue(this.testGet(request));
	}

	/**
	 * 
	 * @param request ClientRequest request
	 * @return boolean
	 */
	public boolean testGet(ClientRequest request) {
		try {
			ClientResponse<SearchFlights> response = request
					.get(SearchFlights.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			SearchFlights result = response.getEntity(SearchFlights.class);
			System.out.println(result.getId());
			System.out.println(result.getTotal());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
