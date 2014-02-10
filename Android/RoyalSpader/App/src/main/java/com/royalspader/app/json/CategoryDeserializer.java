package com.royalspader.app.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.royalspader.app.objects.Category;

import java.lang.reflect.Type;

/**
 * Created by Artwar on 2014-02-05.
 * On projekt App
 */
public class CategoryDeserializer implements JsonDeserializer<Category> {
	@Override
	public Category deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {

		JsonObject jsonObject = jsonElement.getAsJsonObject();
		int id = jsonObject.get("id").getAsInt();
		String name = jsonObject.get("name").getAsString();
		int tokenId = jsonObject.get("@id").getAsInt();

		Category category = new Category();
		category.setId(id);
		category.setName(name);
		category.setTokenId(tokenId);
		return category;
	}
}
