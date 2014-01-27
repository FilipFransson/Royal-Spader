package com.royalspader.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;

/**
 * Created by cissi on 2014-01-22.
 */
public class kassarFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_kassar, container, false);

        ExpandableListView exList = (ExpandableListView)rootView.findViewById(R.id.matkassar);
        myListGroup temp = new myListGroup();
        temp.name = "Hejsan!";
        temp.children.add("Svejsan!");
        ArrayList<myListGroup> tempList = new ArrayList<myListGroup>();
        tempList.add(temp);

        exList.setAdapter(new myListAdapter(getActivity().getBaseContext(),tempList));

        return rootView;
    }

}
