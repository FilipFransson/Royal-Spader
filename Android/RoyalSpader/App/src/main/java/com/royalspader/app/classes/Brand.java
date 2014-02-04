package com.royalspader.app.classes;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by cissi on 2014-02-03.
 */
public class Brand {

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


	public Brand(JSONObject object) {


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

		//"brand":{"@id":2,"id":1,"name":"Carlsberg","orgNumber":"1234567890","address":"colavägen 13 ","phone":"072-302921"},
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
