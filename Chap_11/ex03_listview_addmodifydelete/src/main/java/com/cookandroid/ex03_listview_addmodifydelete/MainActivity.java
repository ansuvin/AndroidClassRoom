package com.cookandroid.ex03_listview_addmodifydelete;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView textView;
    ListView listView;
    Button btnAdd,btnModify,btnDelete;
    ArrayAdapter adapter;

    //리스트뷰에 표시할 데이터
    ArrayList<String> dataList = getStringListForListView(5);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //region 참조변수 연결
        textView = findViewById(R.id.textView);
        listView = findViewById(R.id.listView);
        btnAdd = findViewById(R.id.btnAdd);
        btnModify = findViewById(R.id.btnModify);
        btnDelete = findViewById(R.id.btnDelete);
        //endregion


        //ArrayAdapter 객체 생성 : 각 항목을 표시할 View와 data 삽입
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_single_choice,dataList);

        // 리스트뷰의 각 항목이 라디오 버튼으로 되었을 설정함
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

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

        btnAdd.setOnClickListener(this);
        btnModify.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

    }



    //리스트 뷰에 표시할 리스트 데이터 생성 메소드
    private ArrayList<String> getStringListForListView(int count) {
        ArrayList<String> list = new ArrayList<String>();
        for(int i=0;i<=count;i++){
            list.add("리스트 데이터 "+i);
        }
        return list;
    }

    @Override
    public void onClick(View v) {
        final int checkedIndex = listView.getCheckedItemCount();
        switch (v.getId()){
            case R.id.btnAdd:
                int count = adapter.getCount(); //어댑터에 연결되어 있는 아이템의 개수 저장
                dataList.add("리스트 데이터 "+(count+1));
                break;
            case R.id.btnModify:
                final EditText editText = new EditText(getApplicationContext());
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("리스트 아이템 수정")
                        .setMessage("현재 데이터 : "+dataList.get(checkedIndex))
                        .setIcon(R.mipmap.ic_launcher)
                        .setCancelable(false)
                        .setView(editText)
                        .setPositiveButton("수정", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dataList.set(checkedIndex, editText.getText().toString());
                            }
                        })
                        .setNegativeButton("취소",null)
                        .show();
                break;
            case R.id.btnDelete:
                dataList.remove(checkedIndex);
                listView.clearChoices();
                break;
        }
        adapter.notifyDataSetChanged();     //수정된 상태를 반영
    }
}
