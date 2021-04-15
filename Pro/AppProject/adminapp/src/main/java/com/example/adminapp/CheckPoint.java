package com.example.adminapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class CheckPoint extends AppCompatActivity {

    //region 변수선언
    DatabaseReference myRef;
    FirebaseDatabase database;

    String name, ID, studentID;
    ArrayList<String> reason = new ArrayList<>();
    ArrayList<Integer> point = new ArrayList<>();
    Integer plus = 0, minus = 0;

    ListView listView;
    EditText editReason, editPoint;
    RadioButton btnBonus, btnMinus;
    Button btnReturn, btnGivePoint;
    TextView textPlus, textMinus;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_point);

        //region 생성된 변수에 레이아웃 연결
        listView = findViewById(R.id.pointList);
        editReason = findViewById(R.id.editReason);
        editPoint = findViewById(R.id.editPoint);
        btnBonus = findViewById(R.id.bonus);
        btnMinus = findViewById(R.id.minus);
        btnGivePoint = findViewById(R.id.givePoint);
        btnReturn = findViewById(R.id.btnReturn);
        textPlus = findViewById(R.id.textPlus);
        textMinus = findViewById(R.id.textMinus);
        //endregion

        //region 상벌점 설정하려는 학생 정보 가져오기
        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        ID = intent.getStringExtra("ID");
        studentID = intent.getStringExtra("studentID");
        //endregion

        //region 선택된 학생의 벌점 상태 가져오기
        database = FirebaseDatabase.getInstance();

        myRef = database.getReference("student").child(studentID).child("studentData");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("plus").getValue(Integer.class) != null && snapshot.child("minus").getValue(Integer.class) != null && snapshot.child("pointList").getValue(PointData.class) != null) {
                    plus = snapshot.child("plus").getValue(Integer.class);
                    minus = snapshot.child("minus").getValue(Integer.class);
                    PointData data = snapshot.child("pointList").getValue(PointData.class);
                    reason = data.getReason();
                    point = data.getPoint();
                }
                makeAdapter(reason, point);
                textPlus.setText("상점 : " + plus);
                textMinus.setText("벌점 : " + minus);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //endregion

        //region 버튼 클릭시 벌점 주는 코드

        btnGivePoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editPoint.getText().toString().isEmpty() || editReason.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "사유나 점수를 제대로 적어주세요!", Toast.LENGTH_LONG).show();
                } else {
                    int addpoint = Integer.parseInt(editPoint.getText().toString());
                    if (btnBonus.isChecked()) {
                        reason.add(editReason.getText().toString());
                        point.add(addpoint);
                        plus += addpoint;
                        PointData data = new PointData(reason, point);
                        myRef.child("pointList").setValue(data);
                        myRef.child("plus").setValue(plus);
                    } else if (btnMinus.isChecked()) {
                        reason.add(editReason.getText().toString());
                        point.add(-1*addpoint);
                        minus += addpoint;
                        PointData data = new PointData(reason, point);
                        myRef.child("pointList").setValue(data);
                        myRef.child("minus").setValue(minus);
                    }
                    editReason.setText("");
                    editPoint.setText("");
                    btnBonus.setChecked(false);
                    btnMinus.setChecked(false);
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

    //region 리스트뷰에 어댑터 설정
    private void makeAdapter(ArrayList<String> reason, ArrayList<Integer> point) {

        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

        for(int i = 0; i < reason.size(); i++){
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("reason", reason.get(i));
            hashMap.put("point", point.get(i).toString());

            arrayList.add(hashMap);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, arrayList, R.layout.row_point, new String[]{"reason","point"},new int[]{R.id.reason, R.id.point});
        listView.setAdapter(adapter);

    }

    //endregion
}