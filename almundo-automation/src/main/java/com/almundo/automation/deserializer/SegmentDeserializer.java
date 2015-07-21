package com.almundo.automation.deserializer;

import java.lang.reflect.Type;

import com.almundo.automation.entities.Choice;
import com.almundo.automation.entities.Segment;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class SegmentDeserializer implements JsonDeserializer<Segment> {

	@Override
	public Segment deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		Choice choice;
		Segment segment;
		final JsonObject jsonObject = json.getAsJsonObject();
		final JsonObject jsonSegmentObject = jsonObject
				.getAsJsonObject("segments");

		choice = (Choice) new GsonBuilder()
				.registerTypeAdapter(Choice.class, new ChoiceDeserializer())
				.create().fromJson(jsonSegmentObject, Choice.class);
		segment = new Segment();
		segment.setChoice(choice);
		return segment;

	}

}
