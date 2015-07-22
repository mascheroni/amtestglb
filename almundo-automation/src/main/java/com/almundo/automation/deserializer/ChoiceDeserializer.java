package com.almundo.automation.deserializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.almundo.automation.entities.Choice;
import com.almundo.automation.entities.Leg;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

public class ChoiceDeserializer implements JsonDeserializer<List<Choice>> {

	@Override
	public List<Choice> deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		final JsonObject jsonObject = json.getAsJsonObject();
		final JsonArray jsonClustersArray = jsonObject.get("clusters")
				.getAsJsonArray();
		Choice choice;
		List<Choice> choices = new ArrayList<Choice>();

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
					final JsonObject jsonChoice = (JsonObject) jsonSegmentsArray
							.get(c);
					final JsonArray jsonLegArray = jsonChoice
							.getAsJsonArray("legs");
					
					GsonBuilder gsonBuilder = new GsonBuilder();
					gsonBuilder.registerTypeAdapter(Leg.class, new LegDeserializer());
					Gson gson = gsonBuilder.create();
					final Type legType = new TypeToken<Leg>() {
					}.getType();
					gson = gsonBuilder.create();
					@SuppressWarnings("unchecked")
					List<Leg> legs = (List<Leg>) gson.fromJson(jsonChoice, legType);
					choice = new Choice();
					choice.setLeg(legs);
					choices.add(choice);

				}
			}
		}

		return choices;

	}

}
