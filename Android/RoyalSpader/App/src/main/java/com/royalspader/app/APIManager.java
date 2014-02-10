package com.royalspader.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.royalspader.app.json.BrandDeserializer;
import com.royalspader.app.json.CategoryDeserializer;
import com.royalspader.app.json.StoreDeserializer;
import com.royalspader.app.json.StoreProductDeserializer;
import com.royalspader.app.objects.Brand;
import com.royalspader.app.objects.Category;
import com.royalspader.app.objects.DataModule;
import com.royalspader.app.objects.Product;
import com.royalspader.app.json.ProductDeserializer;
import com.royalspader.app.objects.Store;
import com.royalspader.app.objects.StoreProduct;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cissi on 2014-02-03.
 */
public class APIManager {

	Gson gson;
	Communicator communicator;

	public APIManager(){
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Brand.class, new BrandDeserializer());
		gsonBuilder.registerTypeAdapter(Category.class, new CategoryDeserializer());
		gsonBuilder.registerTypeAdapter(Product.class, new ProductDeserializer());
		gsonBuilder.registerTypeAdapter(Store.class, new StoreDeserializer());
		gsonBuilder.registerTypeAdapter(StoreProduct.class, new StoreProductDeserializer());
		gson = gsonBuilder.create();

		communicator = new Communicator();
	}

    public List<Product> getItems(){

        String URL = communicator.urlBuilder(Communicator.Type.product, "all"); //"http://172.16.6.175:8080/royalspades/api/product/all/";

        return parseProduct(communicator.getData(URL));

    }

	/**
	 * Fyller alla listor i DataModule med data från JSONArray
	 * @param productArray
	 */
    private List<Product> parseProduct(JSONArray productArray) {

        for (int i = 0; i < productArray.length(); i++) {
            try {
	            Product product = gson.fromJson(productArray.getJSONObject(i).toString(), Product.class);
	            DataModule.products.add(product);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
	    return DataModule.products;
    }

	/**
	 * Checks DataModule after Object with the id, else gets data from database
	 * @param id
	 * @return Brand
	 */
	private Brand getBrand(int id){

		for(Brand brand : DataModule.brands){
			if (brand.getId() == id) return brand;
		}
		String url = communicator.urlBuilder(Communicator.Type.brand, String.valueOf(id));
		JSONArray data = communicator.getData(url);
		return gson.fromJson(data.toString(), Brand.class);
	}
	/**
	 * Checks DataModule after Object with the id, else gets data from database
	 * @param id
	 * @return Category
	 */
	private Category getCategory(int id){

		for(Category category : DataModule.categories){
			if (category.getId() == id) return category;
		}
		String url = communicator.urlBuilder(Communicator.Type.category, String.valueOf(id));
		JSONArray data = communicator.getData(url);
		return gson.fromJson(data.toString(), Category.class);
	}
	/**
	 * Checks DataModule after Object with the id, else gets data from database
	 * @param id
	 * @return Product
	 */
	private Product getProduct(int id){

		for(Product product : DataModule.products){
			if (product.getId() == id) return product;
		}
		String url = communicator.urlBuilder(Communicator.Type.product, String.valueOf(id));
		JSONArray data = communicator.getData(url);
		return gson.fromJson(data.toString(), Product.class);
	}
	/**
	 * Checks DataModule after Object with the id, else gets data from database
	 * @param id
	 * @return Store
	 */
	private Store getStore(int id){

		for(Store store : DataModule.stores){
			if (store.getId() == id) return store;
		}
		String url = communicator.urlBuilder(Communicator.Type.store, String.valueOf(id));
		JSONArray data = communicator.getData(url);
		return gson.fromJson(data.toString(), Store.class);
	}

}
