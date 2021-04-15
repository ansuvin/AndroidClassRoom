package com.example.appproject.ui.notifications;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.appproject.ItemNoti;
import com.example.appproject.Notification;
import com.example.appproject.R;

import java.util.ArrayList;

public class NotiAdapter extends BaseAdapter {

    Context context;
    int resource;
    ArrayList<Notification> dataList;

    public NotiAdapter(Context context, int resource, ArrayList<Notification> dataList) {
        this.context = context;
        this.resource = resource;
        this.dataList = dataList;
    }



    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, null);
        }

        TextView titleText = convertView.findViewById(R.id.title);
        TextView contentsText = convertView.findViewById(R.id.contents);
        Button button = convertView.findViewById(R.id.btn_check);

        titleText.setText(dataList.get(position).getTitleStr());
        contentsText.setText(dataList.get(position).getContentStr());

        return convertView;
    }
}
