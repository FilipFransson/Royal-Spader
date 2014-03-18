package com.royalspader.app;

import android.app.Activity;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.royalspader.app.json.BagDeserializer;
import com.royalspader.app.json.BrandDeserializer;
import com.royalspader.app.json.CategoryDeserializer;
import com.royalspader.app.json.StoreDeserializer;
import com.royalspader.app.json.StoreProductDeserializer;
import com.royalspader.app.objects.Bag;
import com.royalspader.app.objects.Brand;
import com.royalspader.app.objects.Category;
import com.royalspader.app.objects.DataModule;
import com.royalspader.app.objects.Product;
import com.royalspader.app.json.ProductDeserializer;
import com.royalspader.app.objects.Store;
import com.royalspader.app.objects.StoreProduct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        gsonBuilder.registerTypeAdapter(Bag.class, new BagDeserializer());
		gson = gsonBuilder.create();

		communicator = new Communicator();
	}
    public String login(Activity activity, JSONObject data){
        //Login URL
        String URL = communicator.urlBuilder("login", null);

        return communicator.postData(activity, URL, data).toString();
    }

    public List<Bag> getBags(Activity activity){
        SharedPreferences sharedPref = activity.getPreferences(activity.getBaseContext().MODE_PRIVATE);

        String token = sharedPref.getString("com.royalspader.app.logintoken", "");
        String URL = "http://dev2-vyh.softwerk.se:8080/royalspades/api/grocerylist/mobile/" + token;
        //String URL = "http://172.16.6.175:8080/royalspades/api/grocerylist/mobile/" + token;
        return parseBag(communicator.getData(URL));
    }

    public List<Product> getProducts(){
        String URL = "http://dev2-vyh.softwerk.se:8080/royalspades/api/product/all/";
        //String URL = "http://172.16.6.175:8080/royalspades/api/product/all/";
        if(DataModule.products.isEmpty() || DataModule.products == null){
            DataModule.products = parseProduct(communicator.getData(URL));
        }

        return DataModule.products;
    }


    public List<Product> getItems(Activity activity, int bagId){

        List<Bag> bags = getBags(activity);

        if(bags.isEmpty()){
            return new ArrayList<Product>();
        }

        for(int i = 0; i < bags.size(); i++){
            if(bags.get(i).id == bagId){
                return bags.get(i).products;
            }
        }
        return null;
    }


    private List<Bag> parseBag(JSONArray arr){
        List<JSONObject> allProducts = new ArrayList<JSONObject>();

        try {
            for (int bagIndex = 0; bagIndex < arr.length(); bagIndex++){

                JSONArray groceryListProducts = arr.optJSONArray(bagIndex);
                if(groceryListProducts == null) break;

                for(int groceryIndex = 0; groceryIndex < groceryListProducts.length(); groceryIndex++){
                    if(groceryListProducts.optJSONObject(groceryIndex) != null){
                        //Is real product
                        allProducts.add(groceryListProducts.optJSONObject(groceryIndex));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //allProducts contains all products
        try {
            for (int bagIndex = 0; bagIndex < arr.length(); bagIndex++){

                JSONArray groceryListProducts = arr.optJSONArray(bagIndex);
                if(groceryListProducts == null) break;
                for(int groceryIndex = 0; groceryIndex < groceryListProducts.length(); groceryIndex++){
                    if(groceryListProducts.optJSONObject(groceryIndex) == null){
                        //Is not a product, get from allProducts
                        int id = groceryListProducts.optInt(groceryIndex);

                        JSONObject product = null;
                        for(int i = 0; i < allProducts.size(); i++){
                            if(allProducts.get(i).getInt("@id") == id){
                                product = allProducts.get(i);
                            }
                        }

                        groceryListProducts.put(groceryIndex, product);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



        for (int i = 0; i < arr.length(); i++) {
            try {
                Bag bag = gson.fromJson(arr.getJSONObject(i).toString(), Bag.class);
                DataModule.bags.add(bag);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return DataModule.bags;
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
        JSONArray URL_Data = null;

        for(Store store : DataModule.stores){
            if (store.getId() == id) return store;
        }
        String url = communicator.urlBuilder(Communicator.Type.store, String.valueOf(id));
        JSONArray data = communicator.getData(url);
        return gson.fromJson(data.toString(), Store.class);
    }
}
