package com.example.adminapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class SetPointActivity extends AppCompatActivity {

    //region 변수 선언
    DatabaseReference myRef;
    FirebaseDatabase database;
    ArrayList<String> nameList;
    ArrayList<String> IDList;
    String name;
    String ID;
    ListView listView;
    Button btnReturn;
    //endregion

    //학생들 이메일 앞부분 따오기 (DB에 넣어둘걸...)
    String[] studentID = {
            "s19066", "s19004", "s19007", "s19048", "s19052",
            "s19010", "s19053", "s19032", "s19011", "s19072",
            "s19013", "s19034", "s19036", "s19057", "s19017",
            "s19018", "s19019", "s19020", "s19038", "s19040"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_point);

        //region 변수 객체에 연결
        nameList = new ArrayList<>();
        IDList = new ArrayList<>();

        listView = findViewById(R.id.listView);
        btnReturn = findViewById(R.id.btnReturn);
        //endregion

        //region 학생 데이터베이스 연결
        database = FirebaseDatabase.getInstance();

        myRef = database.getReference("student");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(int i = 0; i < studentID.length; i++){
                    ID = snapshot.child(studentID[i]).child("studentData").child("ID").getValue(String.class);
                    name = snapshot.child(studentID[i]).child("studentData").child("name").getValue(String.class);

                    if(ID != null || name != null) {
                        IDList.add(ID);
                        nameList.add(name);
                    }

                    makeText(nameList, IDList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //endregion

        //region 학생 선택시 이벤트 처리
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), CheckPoint.class);
                intent.putExtra("name",nameList.get(position));
                intent.putExtra("ID", IDList.get(position));
                intent.putExtra("studentID", studentID[position]);
                startActivity(intent);
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

    //region 학생들 명단 구체화
    private void makeText(ArrayList<String> nameList, ArrayList<String> idList) {

        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

        for(int i = 0; i < nameList.size(); i++){
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("name", nameList.get(i));
            hashMap.put("ID", idList.get(i));

            arrayList.add(hashMap);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, arrayList, android.R.layout.simple_list_item_2, new String[]{"name","ID"},new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(adapter);
    }
    //endregion

}