package com.example.ehddkfl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private ArrayList<MyItem> items = new ArrayList<>();

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public MyItem getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.place_row, parent, false);
        }

        TextView tvCity = convertView.findViewById(R.id.textCity);

        MyItem myItem =getItem(position);
        tvCity.setText(myItem.getCityName());
        return convertView;
    }

    public void addItem(String name){
        MyItem item = new MyItem();

        item.setCityName(name);

        items.add(item);
    }
}
