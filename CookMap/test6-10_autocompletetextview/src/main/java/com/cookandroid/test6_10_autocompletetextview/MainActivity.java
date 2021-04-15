package com.cookandroid.test6_10_autocompletetextview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //자동 완성될 문자열을 배열로 지정
        String[] items = {"CSI-뉴욕","CSI-라스베가스","CSI-마이애미","Friends","Fringe","Lost"};

        //자동완성 텍스트뷰 멀티자동완성 텍스트뷰를 선언 및 대입
        AutoCompleteTextView auto = findViewById(R.id.autoCompleteTextView1);

        //ArrayAdapter는 뷰와 데이터를 연결  (items과 자동완성텍스트뷰를 연결)
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,items);
        auto.setAdapter(adapter);


        //왜 안될까??
        /*MultiAutoCompleteTextView multi = findViewById(R.id.multiAutoCompleteTextView1);
        MultiAutoCompleteTextView.CommaTokenizer token = new MultiAutoCompleteTextView.CommaTokenizer();
        multi.setTokenizer(token);
        multi.setTokenizer(adapter);*/
    }
}
