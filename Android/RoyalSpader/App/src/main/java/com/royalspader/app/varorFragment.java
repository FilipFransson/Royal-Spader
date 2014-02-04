package com.royalspader.app;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by cissi on 2014-01-28.
 */
public class varorFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (android.os.Build.VERSION.SDK_INT>=9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        View rootView = inflater.inflate(R.layout.fragment_grocerys, container, false);

        JSONArray data = new APIManager().getItems();
        JSONObject  temp2 = new JSONObject();

        try {
            temp2 = data.getJSONObject(1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ExpandableListView exList = (ExpandableListView)rootView.findViewById(R.id.grocerys);
        myListGroup temp = new myListGroup();
        temp.name = "Benjamin!";
        //temp.children.add("FilipFix!");
        ArrayList<myListGroup> tempList = new ArrayList<myListGroup>();
        tempList.add(temp);

        //exList.setAdapter(new myListAdapter(getActivity().getBaseContext(),tempList));

        return rootView;
    }
}
