package com.royalspader.app;

import com.google.gson.Gson;
import com.royalspader.app.classes.Brand;
import com.royalspader.app.classes.Category;
import com.royalspader.app.classes.Product;
import com.royalspader.app.classes.Store;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cissi on 2014-02-03.
 */
public class APIManager {

    private List<Product> products = new ArrayList<Product>();
    private List<Store> stores = new ArrayList<Store>();
    private List<Brand> brands = new ArrayList<Brand>();
    private List<Category> categories = new ArrayList<Category>();
	Gson gson = new Gson();


    public List<Product> getItems(){

        String URL = "http://172.16.6.175:8080/royalspades/api/product/all/";

        return parseProduct(getData(URL));

    }

	/**
	 * Fyller alla listor i API manager med data från JSONArray
	 * @param productArray
	 */
    private List<Product> parseProduct(JSONArray productArray) {


        for (int i = 0; i < productArray.length(); i++) {
            try {
                JSONObject jsonProduct = productArray.getJSONObject(i);
                // Find and save Brand

	            Brand brand;
	            try {
		            brand = gson.fromJson(jsonProduct.getJSONObject("brand").toString(), Brand.class);

		            //new Brand(jsonProduct.getJSONObject("brand"));
		            brands.add(brand);

	            }catch (JSONException e){
		            int tokenId = jsonProduct.getInt("brand");
		            brand = findBrand(tokenId);
	            }

	            Category category;
	            try {
		            category = gson.fromJson(jsonProduct.getJSONObject("category").toString(), Category.class);
		            //category = new Category(jsonProduct.getJSONObject("category"));
		            categories.add(category);
	            }catch (JSONException e){
		            int tokenId = jsonProduct.getInt("category");
		            category = findCategory(tokenId);
	            }
				List<Store> storeList = new ArrayList<Store>();
	            JSONArray storeArray = jsonProduct.getJSONArray("storeProducts");
	            for(int i1 = 0; i1 < storeArray.length(); i1++ ){
		            Store store;
		            try {
			            store = gson.fromJson(storeArray.getJSONObject(i1).toString(), Store.class);
			            //store = new Store(storeArray.getJSONObject(i1));
			            stores.add(store);
		            }catch (JSONException e){
			            int tokenId = storeArray.getInt(i1);
			            store = findStore(tokenId);
		            }
		            storeList.add(store);
	            }



	            Product product = new Product(jsonProduct);
	            product.setBrand(brand);
	            product.setCategory(category);
	            product.setStores(storeList);



                products.add(product);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
	    return products;
    }

	/**
	 * Hitta valt Category från Categories-listan och returnera detta
	 * @param tokenID
	 * @return
	 */
	private Category findCategory(int tokenID) {
		int i = 0;
		Category category = null;
		while (categories.get(i).getTokenId() != tokenID) {
			i++;
		}
		if (categories.size() > i) {
			category = categories.get(i);
		}
		return category;
	}

	/**
	 * Hitta valt Brand från Brands-listan och returnera detta
	 * @param tokenID
	 * @return
	 */

	private Brand findBrand(int tokenID){
		int i = 0;
		Brand brand = null;
		while (brands.get(i).getTokenId() != tokenID) {
			i++;
		};
		if (brands.size() > i) {
			brand = brands.get(i);
		}
		return brand;
	}
	/**
	 * Hitta valt Store från Stores-listan och returnera detta
	 * @param tokenID
	 * @return
	 */

	private Store findStore(int tokenID){
		int i = 0;
		Store store = null;
		while (brands.get(i).getTokenId() != tokenID) {
			i++;
		};
		if (brands.size() > i) {
			store = stores.get(i);
		}
		return store;
	}

	/**
	 * Skicka parametrar till Communicator och returnerar JSON data i array
 	 * @param URL
	 * @return
	 */
    private JSONArray getData(String URL){
        JSONArray URL_Data = null;

        try {
            URL_Data = new Communicator().executeHttpGet(URL);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return URL_Data;
    }

}
