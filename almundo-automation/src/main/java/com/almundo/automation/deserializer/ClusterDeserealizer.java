package com.almundo.automation.deserializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.almundo.automation.entities.Cluster;
import com.almundo.automation.entities.Price;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

/**
 * This class deserealize the JSON with the given format
 * 	{
 * 		id: 0,
 * 			{
 * 			- clusters: [
 *						- {
 *							- segments: [],
 *							- price: {
 *									- detail: {},
 *									- total
 *							},
 *						- validating_carrier,
 *						- domestic
 *			}
 * 			]
 * 		}
 * 	} 
 * @author zenen.morales
 *
 */
public class ClusterDeserealizer implements JsonDeserializer<List<Cluster>> {

	public List<Cluster> deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {

		final JsonObject jsonObject = json.getAsJsonObject();
		final JsonArray jsonClustersArray = jsonObject.get("clusters")
				.getAsJsonArray();

		final List<Cluster> clusters = new ArrayList<Cluster>();

		Price price;
		Cluster cluster;
		for (int i = 0; i < jsonClustersArray.size(); i++) {
			final JsonObject jsonClusterObject = (JsonObject) jsonClustersArray
					.get(i);
			final JsonPrimitive validationCarrier = (JsonPrimitive) jsonClusterObject
					.getAsJsonPrimitive("validating_carrier");
			final JsonPrimitive domestic = (JsonPrimitive) jsonClusterObject
					.getAsJsonPrimitive("domestic");

			price = new GsonBuilder()
					.registerTypeAdapter(Price.class, new PriceDeserealizer())
					.create().fromJson(jsonClusterObject, Price.class);

			cluster = new Cluster();
			cluster.setPrice(price);
			cluster.setDomestic(domestic.getAsBoolean());
			cluster.setValidating_carrier(validationCarrier.getAsString());
			clusters.add(cluster);
		}

		return clusters;
	}

}
