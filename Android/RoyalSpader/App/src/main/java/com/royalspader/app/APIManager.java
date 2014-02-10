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

	//private DataModule dataModule = ((DataModule) getApplicationContext());
    private List<Product> products = new ArrayList<Product>();
    private List<Store> stores = new ArrayList<Store>();
    private List<Brand> brands = new ArrayList<Brand>();
    private List<Category> categories = new ArrayList<Category>();
	Gson gson;

	public APIManager(){
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Brand.class, new BrandDeserializer());
		gsonBuilder.registerTypeAdapter(Category.class, new CategoryDeserializer());
		gsonBuilder.registerTypeAdapter(Product.class, new ProductDeserializer());
		gsonBuilder.registerTypeAdapter(Store.class, new StoreDeserializer());
		gsonBuilder.registerTypeAdapter(StoreProduct.class, new StoreProductDeserializer());
		gson = gsonBuilder.create();
	}

    public List<Product> getItems(){

        String URL = "http://172.16.6.175:8080/royalspades/api/product/all/";

        return parseProduct(getData(URL));

    }

	/**
	 * Fyller alla listor i API manager med data från JSONArray
	 * @param productArray
	 */
    private List<Product> parseProduct(JSONArray productArray) {
	    String jsonData = "[{\"@id\":1,\"id\":1,\"name\":\"Coca-Cola\",\"volume\":33.0,\"unit\":\"cc\",\"brand\":{\"@id\":2,\"id\":1,\"name\":\"Carlsberg\",\"orgNumber\":\"1234567890\",\"address\":\"colavägen 13 \",\"phone\":\"072-302921\"},\"category\":{\"@id\":3,\"id\":1,\"name\":\"Soda\"},\"storeProducts\":[{\"category\":3,\"product\":1,\"store\":{\"@id\":4,\"id\":3,\"name\":\"COOP\",\"orgNumber\":\"9876543456789\",\"address\":\"vwbivubv\",\"phone\":\"98-8765678\"}},{\"category\":3,\"product\":1,\"store\":{\"@id\":5,\"id\":2,\"name\":\"ICA\",\"orgNumber\":\"0987654321\",\"address\":\"icavägen 69\",\"phone\":\"666-321789\"}}]},{\"@id\":6,\"id\":2,\"name\":\"Pepsi\",\"volume\":50.0,\"unit\":\"cc\",\"brand\":2,\"category\":{\"@id\":7,\"id\":2,\"name\":\"Stuff\"},\"storeProducts\":[{\"category\":7,\"product\":6,\"store\":{\"@id\":8,\"id\":4,\"name\":\"Konsum\",\"orgNumber\":\"98656785567\",\"address\":\"adhbvwow 456\",\"phone\":\"345-87654\"}},{\"category\":{\"@id\":9,\"id\":3,\"name\":\"Blandat\",\"products\":[]},\"product\":6,\"store\":5},{\"category\":7,\"product\":6,\"store\":5}]},{\"@id\":10,\"id\":3,\"name\":\"Pasta\",\"volume\":500.0,\"unit\":\"g\",\"brand\":2,\"category\":3,\"storeProducts\":[{\"category\":3,\"product\":10,\"store\":8},{\"category\":3,\"product\":10,\"store\":5},{\"category\":7,\"product\":10,\"store\":4}]},{\"@id\":11,\"id\":4,\"name\":\"Fanta\",\"volume\":1.0,\"unit\":\"l\",\"brand\":2,\"category\":7,\"storeProducts\":[{\"category\":7,\"product\":11,\"store\":8}]},{\"@id\":12,\"id\":5,\"name\":\"Sprite\",\"volume\":2.0,\"unit\":\"l\",\"brand\":2,\"category\":3,\"storeProducts\":[{\"category\":3,\"product\":12,\"store\":4}]},{\"@id\":13,\"id\":7,\"name\":\"Milk\",\"volume\":1.0,\"unit\":\"Liter\",\"brand\":2,\"category\":3,\"storeProducts\":[]},{\"@id\":14,\"id\":6,\"name\":\"Potatoes\",\"volume\":1.0,\"unit\":\"l\",\"brand\":{\"@id\":15,\"id\":2,\"name\":\"Östers\",\"orgNumber\":\"98765678\",\"address\":\"sdhbfvwhi 12\",\"phone\":\"098-345676\"},\"category\":7,\"storeProducts\":[]}]";
	    Type productListType = new TypeToken<List<Product>>(){}.getType();

        for (int i = 0; i < productArray.length(); i++) {
            try {
	            Product product = gson.fromJson(productArray.getJSONObject(i).toString(), Product.class);
	            DataModule.products.add(product);
	            /*
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
	            product.setProductStores(storeList);



                products.add(product);
				*/
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
	    return DataModule.products;
    }
<<<<<<< HEAD
    private Brand findBrand(int tokenID){
        int i = 0;
        Brand brand = null;
        while (brands.get(i).getToken() != tokenID) {
            i++;
        };
        brand = brands.get(i);
        return brand;
    }

=======

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
>>>>>>> f925f1882dd011dc19b94b2956cd92bfcb87a88a
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
