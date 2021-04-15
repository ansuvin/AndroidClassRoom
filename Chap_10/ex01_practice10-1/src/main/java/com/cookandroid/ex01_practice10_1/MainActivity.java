package com.cookandroid.ex01_practice10_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNewActivity = findViewById(R.id.btnNewActivity);

        btnNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SecondActivity의 정볼ㄹ 답고있는 인텐드 객체 생성
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent);  //SecondActivity
            }
        });
    }
}
