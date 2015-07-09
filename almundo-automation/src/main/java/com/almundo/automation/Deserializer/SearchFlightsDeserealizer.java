package com.almundo.automation.Deserializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.almundo.POJO.Airline;
import com.almundo.POJO.LowestPricesByAirline;
import com.almundo.POJO.SearchFlights;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * This class deserialize the JSON response of the search flights service
 * 
 * @author zenen.morales
 *
 */
public class SearchFlightsDeserealizer implements
		JsonDeserializer<SearchFlights> {

	@Override
	public SearchFlights deserialize(JsonElement jsonElement, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		final JsonObject jsonObject = jsonElement.getAsJsonObject();
		final JsonElement jsonId = jsonObject.get("id");
		final String id = jsonId.getAsString();
		final JsonArray jsonAirlinesArray = jsonObject.get(
				"lowest_prices_by_airline").getAsJsonArray();

		final List<Airline> airlines = new ArrayList<Airline>();

		for (int i = 0; i < jsonAirlinesArray.size(); i++) {
			final JsonObject jsonObjectAiline = (JsonObject) jsonAirlinesArray
					.get(i);
			final JsonObject jsonAirline = (JsonObject) jsonObjectAiline
					.get("airline");
			final JsonElement jsonCatalog_id = jsonAirline.get("catalog_id");
			final JsonElement jsonCode = jsonAirline.get("code");
			final JsonElement jsonName = jsonAirline.get("name");
			Airline airline = new Airline(jsonCatalog_id.getAsString(),
					jsonCode.getAsString(), jsonName.getAsString());
			airlines.add(airline);
		}

		SearchFlights searchResponse = new SearchFlights();
		searchResponse.setId(id);

		LowestPricesByAirline lowestPricesByAirline = new LowestPricesByAirline();
		lowestPricesByAirline.setAirlines(airlines);
		searchResponse.setLowestPricesByAirline(lowestPricesByAirline);
		return searchResponse;
	}

}
