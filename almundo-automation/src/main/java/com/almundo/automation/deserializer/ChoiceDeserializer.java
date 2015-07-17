package com.almundo.automation.deserializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.almundo.automation.entities.Choice;
import com.almundo.automation.entities.Cluster;
import com.almundo.automation.entities.Leg;
import com.almundo.automation.entities.Price;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

public class ChoiceDeserializer implements JsonDeserializer<List<Choice>> {

	@Override
	public List<Choice> deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		final JsonObject jsonObject = json.getAsJsonObject();
		final JsonArray jsonChoicesArray = jsonObject.get("choices")
				.getAsJsonArray();

		final List<Choice> choices = new ArrayList<Choice>();

		Leg leg;
		Choice choice;
		for (int i = 0; i < jsonChoicesArray.size(); i++) {
			final JsonObject jsonChoiceObject = (JsonObject) jsonChoicesArray
					.get(i);
			final JsonPrimitive idChoice = (JsonPrimitive) jsonChoiceObject
					.getAsJsonPrimitive("id");

			leg = new GsonBuilder()
					.registerTypeAdapter(Leg.class, new LegDeserializer())
					.create().fromJson(jsonChoiceObject, Leg.class);

			choice = new Choice();
			choice.setId(idChoice.getAsInt());
			choice.setLeg(leg);
			choices.add(choice);
		}
		return choices;
	}

}
