package com.royalspader.app.objects;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by cissi on 2014-02-03.
 */
public class Category {

	@SerializedName("id")
	int id;
	@SerializedName("name")
	String name;
	@SerializedName("@id")
	int tokenId;

	//"category":{"id":1,"name":"Soda"},
	public Category(JSONObject object) {

		try {
			id = object.getInt("id");
			name = object.getString("name");
			tokenId = object.getInt("@id");
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	public Category() {

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

	public int getTokenId() {
		return tokenId;
	}

	public void setTokenId(int tokenId) {
		this.tokenId = tokenId;
	}
}
