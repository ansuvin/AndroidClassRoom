package com.cookandroid.ex07_listview_cutomadater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class CustomAdapter extends BaseAdapter {

    Context context;
    int resource;   // 한 항목을 구성하기 위한 View id, R.layout.row
    ArrayList<ItemVO> dataList;

    public CustomAdapter(Context context, int resource, ArrayList<ItemVO> dataList) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        // 재사용 가능한 뷰가 없으면 새로 생성
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.row, null);
        }

        // convertView 세팅(내용 채우기, 이벤트 걸기)
        ImageView imageViewIcon = convertView.findViewById(R.id.imageViewIcon);
        TextView textViewTitle = convertView.findViewById(R.id.textViewTitle);
        TextView textViewContent = convertView.findViewById(R.id.textViewContent);
        ImageView imageViewMenu = convertView.findViewById(R.id.imageViewMenu);

        switch (dataList.get(position).getTypeStr()){
            case "doc":
                imageViewIcon.setImageResource(R.drawable.ic_type_doc);
                break;
            case "img":
                imageViewIcon.setImageResource(R.drawable.ic_type_image);
                break;
            case "file":
                imageViewIcon.setImageResource(R.drawable.ic_type_file);
                break;
        }
        textViewTitle.setText(dataList.get(position).getTitleStr());
        textViewContent.setText(dataList.get(position).getContentStr());
        imageViewMenu.setImageResource(R.drawable.ic_menu);
        return convertView;
    }
}