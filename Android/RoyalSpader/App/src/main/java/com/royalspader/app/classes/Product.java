package com.royalspader.app.classes;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by cissi on 2014-02-03.
 */
public class Product {
    int id;
    String name;
    double volume;
    String unit;
    Brand brand;
    Category category;
    List<Store> stores;



    Product(JSONObject object){

        try {
            id = object.getInt("id");
            name = object.getString("name");
            volume = object.getDouble("volume");
            unit = object.getString("unit");
            brand = new Brand(object.getJSONObject("brand"));
            stores = Store.list(object.getJSONArray("stores"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
