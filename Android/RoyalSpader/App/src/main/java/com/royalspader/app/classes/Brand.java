package com.royalspader.app.classes;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by cissi on 2014-02-03.
 */
public class Brand {

    int id;
    String name;
    String orgNummer;
    String address;
    String phone;
    int tokenId;


    public Brand(JSONObject object) {


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

        //"brand":{"@id":2,"id":1,"name":"Carlsberg","orgNumber":"1234567890","address":"colavägen 13 ","phone":"072-302921"},
    }

    public int getToken() {
        return tokenId;
    }
}
