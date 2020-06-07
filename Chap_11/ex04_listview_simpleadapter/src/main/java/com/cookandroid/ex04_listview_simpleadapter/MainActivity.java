package com.cookandroid.ex04_listview_simpleadapter;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity{

    TextView textView;
    ListView listView;
    Button btnAdd,btnModify,btnDelete;
    ArrayAdapter adapter;

    //리스트뷰에 표시할 데이터
    ArrayList<String> titleData = getStringListForListView(50);
    ArrayList<String> contentsData = getStringListForListView(50);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //region 참조변수 연결
        textView = findViewById(R.id.textView);
        listView = findViewById(R.id.listView);
        //endregion

        final ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
        for(int i=0;i<titleData.size(); i++){
            HashMap<String,String> map = new HashMap<>();
            map.put("title",titleData.get(i));
            map.put("contents",contentsData.get(i));

            arrayList.add(map);
        }

        //ArrayAdapter 객체 생성 : 각 항목을 표시할 View와 data 삽입
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                arrayList,
                android.R.layout.simple_list_item_2,
                new String[]{"title","contents"},
                new int[]{android.R.id.text1,android.R.id.text2});


        // 리스트뷰의 각 항목이 라디오 버튼으로 되었을 설정함
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        //리스트뷰에 adapter 연결
        listView.setAdapter(adapter);

        //리스트뷰의 특징 항목을 클릭했을 때 발생하는 이벤트 처리
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //화면의 TextView에 선택된 항목의 데이터 출력하기
                textView.setText(titleData.get(position)+", "+contentsData.get(position));
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
