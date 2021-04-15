package com.example.appproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RequestSong extends AppCompatActivity {

    //region 변수 선언
    DatabaseReference myRef;
    FirebaseDatabase database;
    Button btnRequest, btnReturn;
    TextView songName, songURL;
    ArrayList<String> name, URL;
    SongData songData;
    ListView listView;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_song);

        //region 변수 객체 연결
        btnRequest = findViewById(R.id.btnRequestSong);
        btnReturn = findViewById(R.id.btnReturn);
        songName = findViewById(R.id.songName);
        songURL = findViewById(R.id.songUrl);
        listView = findViewById(R.id.listView);

        name = new ArrayList<>();
        URL = new ArrayList<>();
        //endregion


        //region 노래 신청 데이터베이스 연결
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("RequestSong");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                SongData data = snapshot.getValue(SongData.class);
                if (data != null) {
                    name = data.getSongName();
                    URL = data.getSongURL();
                    makeAdapter(name, URL);
                }

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

        //region 노래 신청 이벤트 처리
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(songName.getText().toString().isEmpty() || songURL.getText().toString().isEmpty()){
                    Toast.makeText(RequestSong.this, "노래 이름이나 링크를 제대로 적어주세요!",Toast.LENGTH_LONG).show();
                }else {
                    name.add(songName.getText().toString());
                    URL.add(songURL.getText().toString());
                    songData = new SongData(name, URL);
                    myRef.setValue(songData);
                }
            }
        });
        //endregion
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