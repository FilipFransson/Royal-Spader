package com.royalspader.app.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.*;
import com.google.gson.JsonParseException;
import com.royalspader.app.objects.Brand;
import com.royalspader.app.objects.Category;
import com.royalspader.app.objects.DataModule;
import com.royalspader.app.objects.Product;
import com.royalspader.app.objects.StoreProduct;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artwar on 2014-02-05.
 * On projekt App
 */
public class ProductDeserializer implements JsonDeserializer<Product> {
	@Override
	public Product deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {

		final JsonObject jsonObject = jsonElement.getAsJsonObject();

		int id = jsonObject.get("id").getAsInt();
		int tokenId = jsonObject.get("@id").getAsInt();
		String name = jsonObject.get("name").getAsString();
		double volume = jsonObject.get("volume").getAsDouble();
		String unit = jsonObject.get("unit").getAsString();

		final Product product = new Product();
		product.setId(id);
		product.setTokenId(tokenId);
		product.setName(name);
		product.setVolume(volume);
		product.setUnit(unit);
		DataModule.products.add(product);

		Brand brand;
		if (jsonObject.get("brand").isJsonObject()){
			brand = context.deserialize(jsonObject.get("brand"),Brand.class);
			DataModule.brands.add(brand);
		} else {
			brand = DataModule.getBrand(jsonObject.get("brand").getAsInt());
		}

		Category category;
		if (jsonObject.get("category").isJsonObject()){
			category = context.deserialize(jsonObject.get("category"), Category.class);
			DataModule.categories.add(category);
		} else {
			category = DataModule.getCategory(jsonObject.get("category").getAsInt());
		}
		List<StoreProduct> productStores = new ArrayList<StoreProduct>();


		/*JsonArray psArray = jsonObject.get("storeProducts").getAsJsonArray();
		for(int i = 0; i < psArray.size(); i++){
			if (psArray.get(i).isJsonObject()){
				productStores.add(context.<StoreProduct>deserialize(psArray.get(i), StoreProduct.class));
			} else {
				productStores.add(null);
			}
		}
*/


		product.setBrand(brand);
		product.setCategory(category);
		product.setProductStores(productStores);

		return product;
	}
}
