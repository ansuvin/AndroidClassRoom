package com.cookandroid.ex08_spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Spinner spinner;
    ArrayAdapter adapter;

    ArrayList<String> dataList = getArrayListData(30);

    private ArrayList<String> getArrayListData(int cnt) {
        ArrayList<String> list = new ArrayList<>();
        for(int i=1;i<=cnt;i++){
            list.add("리스트 스피너"+i);
        }
        return list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> spinner = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,dataList);

        //드롭 다운 뷰 설정
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
    }
}
