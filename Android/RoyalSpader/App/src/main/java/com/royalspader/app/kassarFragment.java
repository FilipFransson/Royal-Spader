package com.royalspader.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by cissi on 2014-01-22.
 */
public class kassarFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bags, container, false);

        ExpandableListView exList = (ExpandableListView)rootView.findViewById(R.id.bags);
        myListGroup tempGroup = new myListGroup();

        tempGroup.name = formatDate((GregorianCalendar) GregorianCalendar.getInstance());

        MyListChild tempChild = new MyListChild();
        tempChild.name = "Svejsan!";
        tempChild.onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction tx = getActivity().getSupportFragmentManager().beginTransaction();
                tx.replace(R.id.container, new varorFragment());
                tx.commit();
            }
        };

        tempGroup.children.add(tempChild);

        ArrayList<myListGroup> tempList = new ArrayList<myListGroup>();
        tempList.add(tempGroup);

        exList.setAdapter(new myListAdapter(getActivity().getBaseContext(),tempList));

        return rootView;
    }

    private String formatDate(GregorianCalendar calle) {

        String day = "";
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        switch (calle.get(GregorianCalendar.DAY_OF_WEEK)) {
            case 1:
                day = "Söndag";
                break;
            case 2:
                day = "Måndag";
                break;
            case 3:
                day = "Tisdag";
                break;
            case 4:
                day = "Onsdag";
                break;
            case 5:
                day = "Torsdag";
                break;
            case 6:
                day = "Fredag";
                break;
            case 7:
                day = "Lördag";
                break;
        }
        result += day + " ";
        sdf.setCalendar(calle);
        result += sdf.format(calle.getTime());
        return result;
    }

}
