package com.example.justinxu.signinapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by justi on 11/10/2017.
 */

public class ArrayAdapterHandler extends ArrayAdapter {

    List<String[]> list;

    public ArrayAdapterHandler(Context context, int layout, int id, List<String[]> list) {
        super(context, layout, id, list);
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        TextView text1 = (TextView) view.findViewById(android.R.id.text1);
        TextView text2 = (TextView) view.findViewById(android.R.id.text2);

        text1.setText(list.get(position)[0]);
        text2.setText(list.get(position)[1]);
        return view;
    }

    public List<String[]> getList() {
        return list;
    }
}
