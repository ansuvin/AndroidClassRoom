package com.cookandroid.ex06_listview_customadapter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity{

    ListView listView;
    EditText editText;
    Button btn_add;
    ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_add);
        editText = findViewById(R.id.editText);
        listView = findViewById(R.id.listView);

        //리스트뷰에 출력할 조각 데이터 생성
        dataList = geDatatArrayList(5);

        //커스텀 어댑터를 생성
        final CustomAdapter adapter = new CustomAdapter(this, R.layout.row, dataList);

        //리스트부에 어댑터 연결
        listView.setAdapter(adapter);

        //추가 버튼 클릭 이벤트 설정
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //빈 문자열인지 체크
                if(editText.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "문자를 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                //새로운 아이템 추가
                dataList.add(editText.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });

    }

    private ArrayList<String> geDatatArrayList(int count) {
        ArrayList<String> arrayList = new ArrayList<String>();
        for(int i=1; i<=count; i++){
            arrayList.add("리스트 아이템");
        }
        return arrayList;
    }


}
