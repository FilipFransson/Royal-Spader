package com.royalspader.app;

import android.util.Log;
import android.widget.ExpandableListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by cissi on 2014-02-03.
 */
public class APIManager {

    public JSONArray getItems(){
        String URL = "http://172.16.6.175:8080/royalspades/api/product/all/";
        JSONArray URL_Data;
        try {
            URL_Data = new Communicator().executeHttpGet(URL);
            return URL_Data;

        } catch (Exception e) {
            e.printStackTrace();
            return new JSONArray();
        }
        //return null;
    }

}
