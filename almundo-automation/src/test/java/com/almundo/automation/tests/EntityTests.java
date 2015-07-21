package com.almundo.automation.tests;

import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.testng.annotations.Test;

import com.almundo.automation.entities.Choice;
import com.almundo.automation.entities.Leg;
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

	@Test(description = "Verifies the entity Choice", groups = { "entity-test" }, dataProvider = "test1", dataProviderClass = DataProviders.class)
	public void verifyChoiceEntity(Map<String, String> data) {
		String reqDate = this.utils.convertToSpecifDate(data.get("date"));
		data.remove("date");
		data.put("departure", reqDate);
		this.httpClient.setSearchRequest(data);
		System.out.println("Test entities Choice");
		Response response = this.httpClient.post();
		Choice choice =response.getChoice();
		Assert.assertNotNull("The Choice it´s null", choice);
	}
	
	
	@Test(description = "Verifies the entity Leg", groups = { "entity-test" }, dataProvider = "test1", dataProviderClass = DataProviders.class)
	public void verifyLegEntity(Map<String, String> data) {
		String reqDate = this.utils.convertToSpecifDate(data.get("date"));
		data.remove("date");
		data.put("departure", reqDate);
		this.httpClient.setSearchRequest(data);
		System.out.println("Test entities Leg");
		Response response = this.httpClient.post();
		List<Leg> leg= response.getLeg();
		Assert.assertNotNull("The Choice it´s null", leg);
	}
}
