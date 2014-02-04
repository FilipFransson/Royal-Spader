package com.royalspader.app.classes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cissi on 2014-02-03.
 */
public class Store {

    int id;
    String name;
    String orgNummer;
    String address;
    String phone;
    int tokenId;


    public Store(JSONObject object) {


        try {
            id = object.getInt("id");
            name = object.getString("name");
            orgNummer = object.getString("orgNummer");
            address = object.getString("address");
            phone = object.getString("phone");
            tokenId = object.getInt("@id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //{"@id":3,"id":3,"name":"COOP","orgNumber":"9876543456789","address":"vwbivubv","phone":"98-8765678"},
    public Store(JSONArray stores) {

    }

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
}
