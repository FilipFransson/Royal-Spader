package com.royalspader.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by cissi on 2014-01-27.
 */
public class myListAdapter extends BaseExpandableListAdapter{

    ArrayList <myListGroup> Groups;
    Context ctx;

    public myListAdapter(Context ctx, ArrayList<myListGroup> List){
        this.ctx = ctx;
        Groups = List;
    }

    @Override
    public int getGroupCount() {
        return Groups.size();
    }

    @Override
    public int getChildrenCount(int i) {

        int result = 0;
        for (myListGroup g: Groups) {
            result += g.children.size();
        }

        return result;
    }

    @Override
    public Object getGroup(int i) {
        return Groups.get(i);
    }

    @Override
    public Object getChild(int i, int i2) {
        return Groups.get(i).children.get(i2);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i2) {
        return i * 100 + i2;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)ctx.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_group_layout, parent, false);
        }

        TextView groupName = (TextView) v.findViewById(R.id.groupName);

        String name = Groups.get(groupPosition).name;

        groupName.setText(name);

        return v;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)ctx.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_child_layout, parent, false);
        }

        TextView itemName = (TextView) v.findViewById(R.id.itemName);

        String name = Groups.get(groupPosition).children.get(childPosition).name;

        itemName.setText(name);

        v.setOnClickListener(Groups.get(groupPosition).children.get(childPosition).onClickListener);

        return v;

    }

    @Override
    public boolean isChildSelectable(int i, int i2) {
        return false;
    }
}
