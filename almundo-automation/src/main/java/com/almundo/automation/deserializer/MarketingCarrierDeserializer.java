package com.almundo.automation.deserializer;

import java.lang.reflect.Type;

import com.almundo.automation.entities.MarketingCarrier;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

public class MarketingCarrierDeserializer implements
		JsonDeserializer<MarketingCarrier> {

	@Override
	public MarketingCarrier deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		final JsonObject jsonObject = json.getAsJsonObject();
		final JsonObject jsonCarrierObject = jsonObject
				.getAsJsonObject("marketing_carrier");

		MarketingCarrier carrier;

		final JsonPrimitive catalogId = (JsonPrimitive) jsonCarrierObject
				.getAsJsonPrimitive("catalog_id");
		final JsonObject code = (JsonObject) jsonCarrierObject.get("code");
		final JsonObject name = (JsonObject) jsonCarrierObject.get("name");
		carrier = new MarketingCarrier(catalogId.getAsInt(),
				code.getAsString(), name.getAsString());

		return carrier;

	}
}
