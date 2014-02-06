package com.royalspader.app.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.royalspader.app.objects.Store;

import java.lang.reflect.Type;

/**
 * Created by Artwar on 2014-02-05.
 * On projekt App
 */
public class StoreDeserializer implements JsonDeserializer<Store> {
	@Override
	public Store deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {

		JsonObject jsonObject = jsonElement.getAsJsonObject();

		int id = jsonObject.get("id").getAsInt();
		String name = jsonObject.get("name").getAsString();
		String orgNumber = jsonObject.get("orgNumber").getAsString();
		String address = jsonObject.get("address").getAsString();
		String phone = jsonObject.get("phone").getAsString();
		int tokenId = jsonObject.get("@id").getAsInt();

		Store store = new Store();
		store.setId(id);
		store.setName(name);
		store.setOrgNumber(orgNumber);
		store.setAddress(address);
		store.setPhone(phone);
		store.setTokenId(tokenId);
		return store;
	}
}
