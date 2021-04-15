package com.example.adminapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class WriteNotification extends AppCompatActivity {

    //region 변수 선언
    DatabaseReference myRef;
    FirebaseDatabase database;

    ArrayList<String> title = new ArrayList<>();
    ArrayList<String> contents = new ArrayList<>();

    ItemNoti notification;

    Button btnWrite, btnReturn;
    ListView listView;

    EditText edTitle, edContent;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_notification);

        //region 변수에 객체 연결
        btnReturn = findViewById(R.id.returnBtn);
        btnWrite = findViewById(R.id.btnWrite);
        listView = findViewById(R.id.notificationList);
        edTitle = findViewById(R.id.notificationTitle);
        edContent = findViewById(R.id.editNotification);

        //endregion

        //region 데이터베이스 연결
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Notification");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                notification = snapshot.getValue(ItemNoti.class);
                if(notification != null){
                    title = notification.getTitleStr();
                    contents = notification.getContentStr();
                    makeAdapter(title, contents);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //endregion

        //region 공지사항 적는 코드
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edTitle.getText().toString().isEmpty() || !edContent.getText().toString().isEmpty()) {
                    title.add(edTitle.getText().toString());
                    contents.add(edContent.getText().toString());
                    myRef.setValue(new ItemNoti(title, contents));
                }
            }
        });
        //endregion

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //region 쓸데없는 공지사항 삭제하기
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(WriteNotification.this)
                        .setTitle("공지사항을 삭제하시겠습니까?")
                        .setMessage("선택된 공지사항 : " + title.get(position))
                        .setCancelable(false)
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                title.remove(position);
                                contents.remove(position);
                                myRef.setValue(new ItemNoti(title, contents));
                            }
                        })
                        .setNegativeButton("아니오",null).show();
            }
        });
        //endregion


    }

    //region 리스트뷰 어댑터 연결
    private void makeAdapter(ArrayList<String> title, ArrayList<String> contents) {

        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        if(title.size() == 0){

        } else {
            for (int i = 0; i < title.size(); i++) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("title", title.get(i));
                hashMap.put("contents", contents.get(i));

                arrayList.add(hashMap);
            }
        }

        SimpleAdapter adapter = new SimpleAdapter(this, arrayList, android.R.layout.simple_list_item_2, new String[]{"title","contents"},new int[]{android.R.id.text1,android.R.id.text2});
        listView.setAdapter(adapter);

    }

    //endregion
}