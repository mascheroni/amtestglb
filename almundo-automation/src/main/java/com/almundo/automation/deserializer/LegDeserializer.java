package com.almundo.automation.deserializer;

import java.lang.reflect.Type;

import com.almundo.automation.entities.Leg;
import com.almundo.automation.entities.MarketingCarrier;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class LegDeserializer implements JsonDeserializer<Leg> {

	@Override
	public Leg deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		final JsonObject jsonObject = json.getAsJsonObject();
		final JsonObject jsonLegTemp = jsonObject.getAsJsonObject("legs");

		Leg leg;
		leg = new Leg();
		MarketingCarrier carrier;
		carrier = new GsonBuilder()
				.registerTypeAdapter(MarketingCarrier.class,
						new MarketingCarrierDeserializer()).create()
				.fromJson(jsonLegTemp, MarketingCarrier.class);
		leg.setMarketingCarriers(carrier);
		return leg;

	}
}
