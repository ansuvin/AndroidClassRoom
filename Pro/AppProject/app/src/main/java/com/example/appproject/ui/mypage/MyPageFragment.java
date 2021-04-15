package com.example.appproject.ui.mypage;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.appproject.FindPassword;
import com.example.appproject.LoginActivity;
import com.example.appproject.NoticeBreak;
import com.example.appproject.R;
import com.example.appproject.ShowPoint;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyPageFragment extends Fragment {

    //region 변수 선언
    TextView userName, userInfo, plusText, minusText;
    DatabaseReference myRef;
    FirebaseDatabase database;
    Button btnLogout, btnCheckPoint, btnChangePW;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String id = user.getEmail().substring(0,6);
    Button btnNoticebreak, btnSurvey, btnBug;
    //endregion

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, final Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_mypage, container, false);

        //region 학생 정보 데이터베이스에서 가져오기
        database= FirebaseDatabase.getInstance();
        myRef = database.getReference("student").child(id).child("studentData");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String ID = snapshot.child("ID").getValue(String.class);
                String name = snapshot.child("name").getValue(String.class);
                Integer plus = snapshot.child("plus").getValue(Integer.class);
                Integer minus = snapshot.child("minus").getValue(Integer.class);
                if(ID != null || name != null || plus != null || minus != null) {
                    makeText(ID, name,plus,minus);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //endregion

        //region 변수 객체 연결 & 버튼 이벤트 처리
        userName = root.findViewById(R.id.userName);
        userInfo = root.findViewById(R.id.userInfo);
        plusText = root.findViewById(R.id.bonusPoint);
        minusText = root.findViewById(R.id.minusPoint);
        btnChangePW = root.findViewById(R.id.btnChangePW);

        btnLogout = root.findViewById(R.id.btnLogout);
        btnCheckPoint = root.findViewById(R.id.btnCheckPoint);

        btnNoticebreak = root.findViewById(R.id.btnNoticebreak);
        btnSurvey = root.findViewById(R.id.btnSurvey);
        btnBug = root.findViewById(R.id.btnBug);

        btnLogout.setOnClickListener(listener);
        btnCheckPoint.setOnClickListener(listener);
        btnChangePW.setOnClickListener(listener);

        btnNoticebreak.setOnClickListener(listener);
        btnSurvey.setOnClickListener(listener);
        btnBug.setOnClickListener(listener);
        //endregion

        return root;
    }

    //region 회원 정보 텍스트뷰에 띄우기
    private void makeText(String ID, String name, Integer plus, Integer minus) {
        userName.setText(name);
        userInfo.setText(ID);
        plusText.setText(String.valueOf(plus));
        minusText.setText(String.valueOf(minus));
    }
    //endregion

    //region 버튼 클릭 리스너들
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnLogout:
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                    break;
                case R.id.btnCheckPoint:
                    Intent pointIntent = new Intent(getActivity().getApplicationContext(), ShowPoint.class);
                    startActivity(pointIntent);
                    break;
                case R.id.btnChangePW:
                    Intent PWIntent = new Intent(getActivity().getApplicationContext(), FindPassword.class);
                    startActivity(PWIntent);
                    break;
                case R.id.btnNoticebreak:
                    Intent noticeIntent = new Intent(getActivity().getApplicationContext(), NoticeBreak.class);
                    startActivity(noticeIntent);
                    break;
                case R.id.btnSurvey:
                    Intent surveyIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://forms.gle/Wychx46g5KeNPAyt8"));
                    startActivity(surveyIntent);
                    break;
                case R.id.btnBug:
                    Intent bugIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://forms.gle/T9mrCL3hA5KP95mC8"));
                    startActivity(bugIntent);
                    break;
            }
        }
    };
    //endregion
}
