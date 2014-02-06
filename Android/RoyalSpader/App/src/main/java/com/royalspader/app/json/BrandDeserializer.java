package com.royalspader.app.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.royalspader.app.objects.Brand;


import java.lang.reflect.Type;

/**
 * Created by Artwar on 2014-02-05.
 * On projekt App
 */
public class BrandDeserializer implements JsonDeserializer<Brand> {
	@Override
	public Brand deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {

		JsonObject jsonObject = jsonElement.getAsJsonObject();

		int id = jsonObject.get("id").getAsInt();
		String name = jsonObject.get("name").getAsString();
		String orgNumber = jsonObject.get("orgNumber").getAsString();
		String address = jsonObject.get("address").getAsString();
		String phone = jsonObject.get("phone").getAsString();
		int tokenId = jsonObject.get("@id").getAsInt();

		Brand brand = new Brand();
		brand.setId(id);
		brand.setName(name);
		brand.setOrgNumber(orgNumber);
		brand.setAddress(address);
		brand.setPhone(phone);
		brand.setTokenId(tokenId);
		return brand;
	}
}
