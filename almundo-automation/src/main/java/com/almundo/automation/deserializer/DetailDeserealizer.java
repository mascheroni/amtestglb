package com.almundo.automation.deserializer;

import java.lang.reflect.Type;

import com.almundo.automation.entities.Detail;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * 
 * @author zenen.morales
 *
 */
public class DetailDeserealizer implements JsonDeserializer<Detail> {

	@Override
	public Detail deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		Gson gson = new GsonBuilder().create();
		Detail detail = gson.fromJson(json.getAsString(), Detail.class);
		return detail;
	}

}
