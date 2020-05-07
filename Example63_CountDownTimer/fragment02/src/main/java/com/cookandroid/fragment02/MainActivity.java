package com.cookandroid.fragment02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //참조 변수 생성
    Button btn1, btn2;
    LinearLayout linearLayout;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    FragmentOne fragmentOne;
    FragmentTwo fragmentTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //참조변수에 객체 할당
        btn1 = findViewById(R.id.fragment01);
        btn2 = findViewById(R.id.fragment02);
        linearLayout = findViewById(R.id.linearLayout_cotainer);

        //이 Acticity와 관련된 Fragment와 상호작용하기 위한 FragmentManager 봔환
        fragmentManager = getSupportFragmentManager();
        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }


    //버튼이 클릭되면 해당 프로그먼트를 화면에 표시한다.
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment01:
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.linearLayout_cotainer, fragmentOne);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.fragment02:
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.linearLayout_cotainer, fragmentTwo);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
        }
    }
}
