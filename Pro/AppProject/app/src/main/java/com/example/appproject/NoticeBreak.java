package com.example.appproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NoticeBreak extends AppCompatActivity {

    //region 변수 선언
    DatabaseReference myRef;
    FirebaseDatabase database;
    Button btnWriteBreak, btnReturn;
    EditText edTitle, edContents;
    BreakData data;
    ArrayList<String> breakTitle = new ArrayList<>();
    ArrayList<String> breakContents = new ArrayList<>();
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_break);

        //region 변수에 객체 연결
        edTitle = findViewById(R.id.breakTitle);
        edContents = findViewById(R.id.breakContent);
        btnWriteBreak = findViewById(R.id.btnWriteBreak);
        btnReturn = findViewById(R.id.btnBack);
        //endregion

        //region 분실신고 데이터베이스 가져오기
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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //endregion

        //region 분실 신고 버튼 클릭 이벤트 처리
        btnWriteBreak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edTitle.getText().toString().isEmpty() && !edContents.getText().toString().isEmpty()){
                    breakTitle.add(edTitle.getText().toString());
                    breakContents.add(edContents.getText().toString());
                    myRef.setValue(new BreakData(breakTitle, breakContents));
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
    }
}