package com.almundo.automation.deserializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.almundo.automation.entities.Choice;
import com.almundo.automation.entities.Segment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

public class SegmentDeserializer implements JsonDeserializer<List<Segment>> {

	@Override
	public List<Segment> deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		Segment segment;
		List<Segment> segments = new ArrayList<Segment>();
		final JsonObject jsonObject = json.getAsJsonObject();
		final JsonArray jsonClusterArray = jsonObject.get("clusters")
				.getAsJsonArray();
		for (int i = 0; i < jsonClusterArray.size(); i++) {
			final JsonObject cluster = (JsonObject) jsonClusterArray.get(i);
			final JsonArray jsonSegmentArray = cluster.get("segments")
					.getAsJsonArray();
			for (int j = 0; j < jsonSegmentArray.size(); j++) {
				final JsonObject segmentTemp = (JsonObject) jsonSegmentArray
						.get(j);
				GsonBuilder gsonBuilder = new GsonBuilder();
				gsonBuilder.registerTypeAdapter(Choice.class, new ChoiceDeserializer());
				Gson gson = gsonBuilder.create();
				final Type choiceType = new TypeToken<Choice>() {
				}.getType();
				gson = gsonBuilder.create();
				@SuppressWarnings("unchecked")
				List<Choice> choices = (List<Choice>) gson.fromJson(segmentTemp,
						choiceType);
				segment = new Segment();
				segment.setChoice(choices);
				segments.add(segment);
			}
		}

		return segments;

	}

}
