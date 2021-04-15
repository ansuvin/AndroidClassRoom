package com.example.adminapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class CheckLaptop extends AppCompatActivity {

    //region 변수선언
    DatabaseReference myRef;
    FirebaseDatabase database;
    ArrayList<String> studentName = new ArrayList<>();
    ArrayList<String> studentID = new ArrayList<>();

    Laptop laptop;

    ListView listView;

    Button btnReturn;

    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH)+1;
    int date = calendar.get(Calendar.DATE);
    String dateID = ""+year+month+date;

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_laptop);
        listView = findViewById(R.id.LaptopList);
        btnReturn = findViewById(R.id.btnBack);

        //region 오늘 노트북 신청자 명단 가져오는 코드
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("RequestLaptop").child(dateID);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                laptop = snapshot.getValue(Laptop.class);
                if(laptop != null) {
                    studentName = laptop.getLaptopList();
                    studentID = laptop.getLaptopID();
                }
                makeAdapter(studentName, studentID);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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

    //region 리스트뷰 어댑터 연결
    private void makeAdapter(ArrayList<String> name, ArrayList<String> url) {

        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        for(int i = 0; i < name.size(); i++){
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("title", name.get(i));
            hashMap.put("contents",url.get(i));

            arrayList.add(hashMap);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, arrayList, android.R.layout.simple_list_item_2, new String[]{"title","contents"},new int[]{android.R.id.text1,android.R.id.text2});
        listView.setAdapter(adapter);

    }
    //endregion
}