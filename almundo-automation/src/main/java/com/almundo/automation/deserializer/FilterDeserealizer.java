package com.almundo.automation.deserializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.almundo.automation.entities.Filter;
import com.almundo.automation.entities.Value;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

public class FilterDeserealizer implements JsonDeserializer<List<Filter>> {

	@Override
	public List<Filter> deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		final JsonObject jsonObject = json.getAsJsonObject();
		final JsonArray jsonFilterArray = jsonObject.get("filters")
				.getAsJsonArray();

		Value value;
		Filter filter;
		List<Filter> filters = new ArrayList<Filter>();
		final List<Value> values = new ArrayList<Value>();

		for (int i = 0; i < jsonFilterArray.size(); i++) {
			final JsonObject jsonValuesObject = (JsonObject) jsonFilterArray
					.get(i);
			final JsonPrimitive key = (JsonPrimitive) jsonValuesObject
					.getAsJsonPrimitive("key");
			final JsonPrimitive type = (JsonPrimitive) jsonValuesObject.get("type");
			final JsonArray jsonValuesArray = jsonObject.get("values")
					.getAsJsonArray();
			for (int v = 0; v < jsonValuesArray.size(); v++) {
				final JsonObject jsonValueObject = (JsonObject) jsonFilterArray
						.get(i);
				final JsonPrimitive quantity = (JsonPrimitive) jsonValueObject
						.getAsJsonPrimitive("quantity");
				final JsonObject code = (JsonObject) jsonValueObject
						.get("code");
				final JsonObject name = (JsonObject) jsonValueObject
						.get("name");
				value = new Value(quantity.getAsInt(), code.getAsString(),
						name.getAsString());
				values.add(value);
			}

			filter = new Filter(key.getAsString(), type.getAsString(), values);
			filters.add(filter);
		}

		return filters;
	}

}
