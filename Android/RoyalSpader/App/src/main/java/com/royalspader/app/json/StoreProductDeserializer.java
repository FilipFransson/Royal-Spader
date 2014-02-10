package com.royalspader.app.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.royalspader.app.objects.Category;
import com.royalspader.app.objects.DataModule;
import com.royalspader.app.objects.Product;
import com.royalspader.app.objects.Store;
import com.royalspader.app.objects.StoreProduct;

import java.lang.reflect.Type;

/**
 * Created by Artwar on 2014-02-05.
 * On projekt App
 */
public class StoreProductDeserializer implements JsonDeserializer<StoreProduct> {
	@Override
	public StoreProduct deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {

		JsonObject jsonObject = jsonElement.getAsJsonObject();
		int tokenId = -1;
		if (jsonObject.has("@id")){
			tokenId = jsonObject.get("@id").getAsInt();
		}

		Category category;
		if (jsonObject.get("category").isJsonObject()){
			category = context.deserialize(jsonObject.get("category"), Category.class);
			DataModule.categories.add(category);
		} else {
			int temp = jsonObject.get("category").getAsInt();

			category = DataModule.getCategory(temp);
		}
		Product product;
		if (jsonObject.get("product").isJsonObject()){
			product = context.deserialize(jsonObject.get("product"), Product.class);
			DataModule.products.add(product);
		} else {
			product = DataModule.getProduct(jsonObject.get("product").getAsInt());
		}
		Store store;
		if (jsonObject.get("store").isJsonObject()){
			store = context.deserialize(jsonObject.get("store"), Store.class);
			DataModule.stores.add(store);
		} else {
			store = DataModule.getStore(jsonObject.get("store").getAsInt());
		}

		StoreProduct storeProduct = new StoreProduct();
		storeProduct.setTokenId(tokenId);
		storeProduct.setCategory(category);
		storeProduct.setProduct(product);
		storeProduct.setStore(store);
		return storeProduct;
	}
}
