package com.almundo.automation.deserializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.almundo.automation.entities.MarketingCarrier;
import com.almundo.automation.entities.OperatingCarrier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

public class MarketingCarrierDeserializer implements
		JsonDeserializer<List<MarketingCarrier>> {

	@Override
	public List<MarketingCarrier> deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		final JsonObject jsonObject = json.getAsJsonObject();
		final JsonArray jsonClustersArray = jsonObject.get("clusters")
				.getAsJsonArray();
		List<MarketingCarrier> marketingCarriers = new ArrayList<MarketingCarrier>();

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
						MarketingCarrier marketingCarrier = gson.fromJson(
								jsonLeg.get("marketing_carrier"),
								MarketingCarrier.class);
						marketingCarriers.add(marketingCarrier);
					}
				}
			}
		}
		return marketingCarriers;
	}
}
