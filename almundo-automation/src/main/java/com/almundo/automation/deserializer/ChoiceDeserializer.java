package com.almundo.automation.deserializer;

import java.lang.reflect.Type;

import com.almundo.automation.entities.Choice;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class ChoiceDeserializer implements JsonDeserializer<Choice> {

	@Override
	public Choice deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		final JsonObject jsonObject = json.getAsJsonObject();
		final JsonObject jsonChoice = jsonObject.getAsJsonObject("choices");
		final JsonElement jsonId = jsonChoice.get("id");
		final String id = jsonId.getAsString();

		Choice choice;


		choice = new Choice();
		choice.setId(id);
		return choice;
	}

}
