package com.royalspader.app.objects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Artwar on 2014-02-05.
 * On projekt App
 */
public class StoreProduct {
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public int getTokenId() {
		return tokenId;
	}

	public void setTokenId(int tokenId) {
		this.tokenId = tokenId;
	}

	//"storeProducts":[
	//    {"category":3,
	//    "product":1,
	//    "store":{"@id":4,"id":3,"name":"COOP","orgNumber":"9876543456789","address":"vwbivubv","phone":"98-8765678"}},
	//    {"category":3,
	//    "product":1,
	//    "store":{"@id":5,"id":2,"name":"ICA","orgNumber":"0987654321","address":"icavägen 69","phone":"666-321789"}}
	// ]
	@SerializedName("category")
	Category category;
	@SerializedName("product")
	Product product;
	@SerializedName("store")
	Store store;
	@SerializedName("@id")
	int tokenId;
}
