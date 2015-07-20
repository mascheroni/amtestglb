package com.almundo.automation.tests;

import java.util.Map;

import junit.framework.Assert;

import org.testng.annotations.Test;

import com.almundo.automation.entities.Segment;
import com.almundo.automation.services.Response;
import com.almundo.automation.utils.DataProviders;

public class EntityTests extends BaseTest {

	@Test(description = "Verifies the entity Segment", groups = { "entity-test" }, dataProvider = "test1", dataProviderClass = DataProviders.class)
	public void verifySegmentEntity(Map<String, String> data) {
		String reqDate = this.utils.convertToSpecifDate(data.get("date"));
		data.remove("date");
		data.put("departure", reqDate);
		this.httpClient.setSearchRequest(data);
		System.out.println("Test entities");
		Response response = this.httpClient.post();
		Segment segment = response.getSegments();
		Assert.assertNotNull("The Segment it´s null", segment);
	}
}
