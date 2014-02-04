package com.royalspader.app;

import android.util.Log;
import android.widget.ExpandableListView;

import com.royalspader.app.classes.Brand;
import com.royalspader.app.classes.Category;
import com.royalspader.app.classes.Product;
import com.royalspader.app.classes.Store;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cissi on 2014-02-03.
 */
public class APIManager {

    private List<Product> products = new ArrayList<Product>();
    private List<Store> stores = new ArrayList<Store>();
    private List<Brand> brands = new ArrayList<Brand>();
    private List<Category> categories = new ArrayList<Category>();


    public List<Product> getItems(){

        String URL = "http://172.16.6.175:8080/royalspades/api/product/all/";

        return Product.list(getData(URL));

    }

    private void parseProduct(JSONArray productArray) {


        for (int i = 0; i < productArray.length(); i++) {
            try {
                JSONObject jsonProduct = productArray.getJSONObject(i);
                // Find and save Brand
                if (!jsonProduct.getJSONObject("Brand").has("id")){
                    int tokenId = jsonProduct.getInt("Brand");
                    findBrand(tokenId);

                }
                products.add(new Product(jsonProduct));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private Brand findBrand(int tokenID){
        int i = 0;
        Brand brand = null;
        while (brands.get(i).getToken() != tokenID) {
            i++;
        };
        brand = brands.get(i);
        return brand;
    }
         
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
