package com.royalspader.app.classes;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by cissi on 2014-02-03.
 */
public class Category {

    int id;
    String name;

    //"category":{"id":1,"name":"Soda"},
    public Category(JSONObject object) {

        try {
            id = object.getInt("id");
            name = object.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
