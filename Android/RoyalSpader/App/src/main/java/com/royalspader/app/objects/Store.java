package com.royalspader.app.objects;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cissi on 2014-02-03.
 */
public class Store {

	@SerializedName("id")
	int id;
	@SerializedName("name")
	String name;
	@SerializedName("orgNumber")
	String orgNumber;
	@SerializedName("address")
	String address;
	@SerializedName("phone")
	String phone;
	@SerializedName("@id")
	int tokenId;


	public Store(JSONObject object) {


		try {
			id = object.getInt("id");
			name = object.getString("name");
			orgNumber = object.getString("orgNumber");
			address = object.getString("address");
			phone = object.getString("phone");
			tokenId = object.getInt("@id");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public Store() {

	}
	//{"@id":3,"id":3,"name":"COOP","orgNumber":"9876543456789","address":"vwbivubv","phone":"98-8765678"},

	public static List<Store> list(JSONArray storeArray) {
		List<Store> stores = new ArrayList<Store>();
		for (int i = 0; i < storeArray.length(); i++) {
			try {
				stores.add(new Store(storeArray.getJSONObject(i)));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return stores;
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

	public String getOrgNumber() {
		return orgNumber;
	}

	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getTokenId() {
		return tokenId;
	}

	public void setTokenId(int tokenId) {
		this.tokenId = tokenId;
	}
}
