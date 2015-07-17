package com.almundo.automation.deserializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.almundo.automation.entities.Choice;
import com.almundo.automation.entities.Segment;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class SegmentDeserializer implements JsonDeserializer<List<Segment>> {

	@Override
	public List<Segment> deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		List<Choice> choices  = new ArrayList<Choice>();
		Segment segment;
		List<Segment> segments = new ArrayList<Segment>();
		final JsonObject jsonObject = json.getAsJsonObject();
		final JsonArray jsonSegmentArray = jsonObject.get("segments").getAsJsonArray();
		
		for(int i = 0; i< jsonSegmentArray.size(); i++){
			final JsonObject jsonSegmentObject = (JsonObject)jsonSegmentArray.get(i);
			choices = (List<Choice>) new GsonBuilder()
			.registerTypeAdapter(Choice.class, new ChoiceDeserializer())
			.create().fromJson(jsonSegmentObject, Choice.class);
		segment = new Segment();
		segment.setChoices(choices);
		segments.add(segment);
		}
		return segments;
		
		
	}

}
