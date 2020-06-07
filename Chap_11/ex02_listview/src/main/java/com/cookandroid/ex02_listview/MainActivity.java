package com.cookandroid.ex02_listview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ListView listView;

    //리스트뷰에 표시할 데이터
    ArrayList<String> dataList = getStringListForListView(100);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text1);
        listView = findViewById(R.id.listView);

        //ArrayAdapter 객체 생성 : 각 항목을 표시할 View와 data 삽입
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.row,R.id.text1, dataList);

        //리스트뷰에 adapter 연결
        listView.setAdapter(adapter);

        //리스트뷰의 특징 항목을 클릭했을 때 발생하는 이벤트 처리
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //화면의 TextView에 선택된 항목의 데이터 출력하기
                textView.setText(dataList.get(position));
            }
        });
    }


    //리스트 뷰에 표시할 리스트 데이터 생성 메소드
    private ArrayList<String> getStringListForListView(int count) {
        ArrayList<String> list = new ArrayList<String>();
        for(int i=0;i<=count;i++){
            list.add("리스트 데이터 "+i);
        }
        return list;
    }
}
