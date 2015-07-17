package com.almundo.automation.deserializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.almundo.automation.entities.Leg;
import com.almundo.automation.entities.MarketingCarrier;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

public class LegDeserializer implements JsonDeserializer<List<Leg>> {

	@Override
	public List<Leg> deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		final JsonObject jsonObject = json.getAsJsonObject();
		final JsonArray jsonLegsArray = jsonObject.get("legs")
				.getAsJsonArray();

		MarketingCarrier carrier;
		Leg leg;
		List<Leg> legs = new ArrayList<Leg>();
		final List<MarketingCarrier> marketingCarriers = new ArrayList<MarketingCarrier>();
		for (int i = 0; i < jsonLegsArray.size(); i++) {
			final JsonArray jsonCarriersArray = jsonObject.get(
					"marketing_carrier").getAsJsonArray();
			for (int v = 0; v < jsonCarriersArray.size(); v++) {
				final JsonObject jsonCarrierObject = (JsonObject) jsonLegsArray
						.get(i);
				final JsonPrimitive catalogId = (JsonPrimitive) jsonCarrierObject
						.getAsJsonPrimitive("catalog_id");
				final JsonObject code = (JsonObject) jsonCarrierObject
						.get("code");
				final JsonObject name = (JsonObject) jsonCarrierObject
						.get("name");
				carrier = new MarketingCarrier(catalogId.getAsInt(),
						code.getAsString(), name.getAsString());
				marketingCarriers.add(carrier);
			}

		}
		leg = new Leg(marketingCarriers);
		legs.add(leg);

		return legs;

	}
}
