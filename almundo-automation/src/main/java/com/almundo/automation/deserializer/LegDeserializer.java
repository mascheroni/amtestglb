package com.almundo.automation.deserializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.almundo.automation.entities.Leg;
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
		final JsonArray jsonLegsArray = jsonObject.get("legs").getAsJsonArray();

		Leg leg;
		List<Leg> legs = new ArrayList<Leg>();

		for (int i = 0; i < jsonLegsArray.size(); i++) {
			final JsonObject jsonLegObject = (JsonObject) jsonLegsArray.get(i);
			final JsonPrimitive jsonNumber = jsonLegObject
					.getAsJsonPrimitive("number");
			leg = new Leg();
			leg.setNumber(jsonNumber.getAsInt());
			legs.add(leg);

		}

		return legs;

	}
}
