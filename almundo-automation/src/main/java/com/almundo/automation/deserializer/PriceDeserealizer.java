package com.almundo.automation.deserializer;

import java.lang.reflect.Type;

import com.almundo.automation.entities.Detail;
import com.almundo.automation.entities.Price;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

/**
 * This class deserialize the JSON with the given format
 * 	- segments : {
 * 		- detail: {
 * 					adults: 0,
 * 					children: 0,
 * 					infant: 0,
 * 					taxes: 0.0,
 * 					extra_tax: 0.0
 * 		}
 * }
 * @author zenen.morales
 *
 */
public class PriceDeserealizer implements JsonDeserializer<Price> {

	@Override
	public Price deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		final JsonObject jsonObject = json.getAsJsonObject();
		final JsonObject jsonPriceObject = jsonObject.getAsJsonObject("price");
		final JsonPrimitive jsonTotal = (JsonPrimitive) jsonPriceObject
				.getAsJsonPrimitive("total");

		Gson gson = new GsonBuilder().create();
		Detail detail = gson.fromJson(jsonPriceObject.get("detail"),
				Detail.class);

		Price price = new Price();
		price.setTotal(jsonTotal.getAsInt());
		price.setDetail(detail);
		return price;
	}

}
