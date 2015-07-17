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
			final JsonObject jsonFilterObject = (JsonObject) jsonFilterArray
					.get(i);
			final JsonPrimitive key = (JsonPrimitive) jsonFilterObject
					.getAsJsonPrimitive("key");
			final JsonPrimitive type = (JsonPrimitive) jsonFilterObject
					.getAsJsonPrimitive("type");

			if (this.getNullAsEmptyString(jsonFilterObject
					.getAsJsonArray("values"))) {
				final JsonArray jsonValuesArray = jsonFilterObject
						.getAsJsonArray("values");

				for (int v = 0; v < jsonValuesArray.size(); v++) {
					final JsonObject jsonValueObject = (JsonObject) jsonValuesArray
							.get(v);
					final JsonPrimitive quantity = (JsonPrimitive) jsonValueObject
							.getAsJsonPrimitive("quantity");
					final JsonPrimitive code = (JsonPrimitive) jsonValueObject
							.getAsJsonPrimitive("code");
					final JsonPrimitive name = (JsonPrimitive) jsonValueObject
							.getAsJsonPrimitive("name");
					value = new Value(quantity.getAsInt(), code.getAsString(),
							name.getAsString());
					values.add(value);
				}
			}
			filter = new Filter(key.getAsString(), type.getAsString(), values);
			filters.add(filter);
		}

		return filters;
	}

	private boolean getNullAsEmptyString(JsonArray jsonArray) {
		try {
			return jsonArray.isJsonNull() ? false : true;
		} catch (Exception e) {
			return false;
		}
	}
}
