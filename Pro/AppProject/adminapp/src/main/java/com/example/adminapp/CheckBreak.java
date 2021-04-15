package com.example.adminapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class CheckBreak extends AppCompatActivity {

    DatabaseReference myRef;
    FirebaseDatabase database;
    BreakData data;
    ArrayList<String> breakTitle = new ArrayList<>();
    ArrayList<String> breakContents = new ArrayList<>();
    ListView listView;
    Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_break);

        listView = findViewById(R.id.breakList);
        btnReturn = findViewById(R.id.btnBreakBack);


        //region 고장난 시설의 이름, 고장 상태 받아오기
        database= FirebaseDatabase.getInstance();
        myRef = database.getReference("NoticeBreak");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data = snapshot.getValue(BreakData.class);
                if(data != null){
                    breakTitle = data.getBreakTitle();
                    breakContents = data.getBreakContents();
                }
                makeAdapter(breakTitle, breakContents);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //endregion

        //region 수리가 완료되었을때 완료 표시하기
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(CheckBreak.this)
                        .setTitle("수리가 완료 되었습니까?")
                        .setMessage("선택된 시설 : " + breakTitle.get(position))
                        .setCancelable(false)
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                breakTitle.remove(position);
                                breakContents.remove(position);
                                myRef.setValue(new BreakData(breakTitle, breakContents));
                            }
                        })
                        .setNegativeButton("아니오",null).show();
            }
        });
        //endregion

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //region 리스트뷰 어댑터 생성
    private void makeAdapter(ArrayList<String> breakTitle, ArrayList<String> breakContents) {

        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

        for(int i = 0; i < breakTitle.size(); i++){
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("breakTitle", breakTitle.get(i));
            hashMap.put("breakContents", breakContents.get(i));

            arrayList.add(hashMap);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, arrayList, android.R.layout.simple_list_item_2, new String[]{"breakTitle","breakContents"},new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(adapter);
    }
    //endregion

}