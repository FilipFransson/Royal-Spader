package com.royalspader.app.classes;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cissi on 2014-02-03.
 */
public class Product {

	@SerializedName("id")
	int id;
	@SerializedName("name")
	String name;
	@SerializedName("volume")
	double volume;
	@SerializedName("unit")
	String unit;
	@SerializedName("brand")
	Brand brand;
	@SerializedName("category")
	Category category;
	@SerializedName("stores")
	List<Store> stores;
	@SerializedName("@id")
	int tokenId;


	public Product(JSONObject object) {

		try {
			id = object.getInt("id");
			name = object.getString("name");
			volume = object.getDouble("volume");
			unit = object.getString("unit");
			brand = new Brand(object.getJSONObject("brand"));
			category = new Category(object.getJSONObject("category"));
			stores = Store.list(object.getJSONArray("stores"));
			tokenId = object.getInt("@id");
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	public Product() {

	}

	public static List<Product> list(JSONArray productArray) {
		List<Product> product = new ArrayList<Product>();
		for (int i = 0; i < productArray.length(); i++) {
			try {
				product.add(new Product(productArray.getJSONObject(i)));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Store> getStores() {
		return stores;
	}

	public void setStores(List<Store> stores) {
		this.stores = stores;
	}

	public int getTokenId() {
		return tokenId;
	}

	public void setTokenId(int tokenId) {
		this.tokenId = tokenId;
	}
	/*
    [{"@id":1,
    "id":1,
    "name":"Coca-Cola",
    "volume":33.0,
    "unit":"cc",
    "brand":{"@id":2,"id":1,"name":"Carlsberg","orgNumber":"1234567890","address":"colavägen 13 ","phone":"072-302921"},
    "category":{"id":1,"name":"Soda"},
    "stores":[
        {"@id":3,"id":3,"name":"COOP","orgNumber":"9876543456789","address":"vwbivubv","phone":"98-8765678"},
        {"@id":4,"id":2,"name":"ICA","orgNumber":"0987654321","address":"icavägen 69","phone":"666-321789"}
        ]},

    {"@id":5,
    "id":3,
    "name":"Pasta",
    "volume":500.0,
    "unit":"g",
    "brand":2,
    "category":{"id":1,"name":"Soda"},
    "stores":[
        3,
        {"@id":6,"id":4,"name":"Konsum","orgNumber":"98656785567","address":"adhbvwow 456","phone":"345-87654"},
        4
        ]},

    {"@id":7,
    "id":5,
    "name":"Sprite",
    "volume":2.0,
    "unit":"l",
    "brand":2,
    "category":{"id":1,"name":"Soda"},
    "stores":[3]},

    {"@id":8,
    "id":2,
    "name":"Pepsi",
    "volume":50.0,
    "unit":"cc",
    "brand":2,
    "category":{"id":2,"name":"Stuff"},
    "stores":[6,4]},

    {"@id":9,"id":4,"name":"Fanta","volume":1.0,"unit":"l","brand":2,"category":{"id":2,"name":"Stuff"},"stores":[6,4]},{"@id":10,"id":6,"name":"Potatoes","volume":1.0,"unit":"l","brand":{"@id":11,"id":2,"name":"Östers","orgNumber":"98765678","address":"sdhbfvwhi 12","phone":"098-345676"},"category":{"id":2,"name":"Stuff"},"stores":[]}]
     */
}
