package com.cookandroid.ex04_example10_16;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class secondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button btnReturn = findViewById(R.id.btnReturn);

        //인텐트 받아서, 데이터 꺼내기
        Intent InIntent = getIntent();
        final int hap = InIntent.getIntExtra("Num1",0)
                +InIntent.getIntExtra("Num2",0);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent(getApplicationContext(), MainActivity.class);
                outIntent.putExtra("Hap", hap);
                setResult(RESULT_OK, outIntent);
                finish();
            }
        });
    }
}
