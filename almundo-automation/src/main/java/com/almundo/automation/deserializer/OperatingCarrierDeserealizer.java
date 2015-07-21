package com.almundo.automation.deserializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.almundo.automation.entities.OperatingCarrier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class OperatingCarrierDeserealizer implements
		JsonDeserializer<List<OperatingCarrier>> {

	@Override
	public List<OperatingCarrier> deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		final JsonObject jsonObject = json.getAsJsonObject();
		final JsonArray jsonClustersArray = jsonObject.get("clusters")
				.getAsJsonArray();
		List<OperatingCarrier> operatingCarriers = new ArrayList<OperatingCarrier>();

		for (int i = 0; i < jsonClustersArray.size(); i++) {
			final JsonObject jsonCluster = (JsonObject) jsonClustersArray
					.get(i);
			final JsonArray jsonSegmentsArray = jsonCluster
					.getAsJsonArray("segments");
			for (int s = 0; s < jsonSegmentsArray.size(); s++) {
				final JsonObject jsonSegment = (JsonObject) jsonSegmentsArray
						.get(s);
				final JsonArray jsonChoicesArray = jsonSegment
						.getAsJsonArray("choices");
				for (int c = 0; c < jsonChoicesArray.size(); c++) {
					final JsonObject jsonChoice = (JsonObject) jsonChoicesArray
							.get(c);
					final JsonArray jsonLegsArray = jsonChoice
							.getAsJsonArray("legs");
					for (int l = 0; l < jsonLegsArray.size(); l++) {
						final JsonObject jsonLeg = (JsonObject) jsonLegsArray
								.get(l);
						Gson gson = new GsonBuilder().create();
						OperatingCarrier operatingCarrier = gson.fromJson(
								jsonLeg.get("operating_carrier"),
								OperatingCarrier.class);
						operatingCarriers.add(operatingCarrier);
					}
				}
			}
		}
		return operatingCarriers;
	}

}
