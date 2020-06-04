package com.cookandroid.ex05_diy10_3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class secondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button btnReturn = findViewById(R.id.btnReturn);

        //인텐트 받아서, 데이터 꺼내기
        Intent InIntent = getIntent();
        int num1 = InIntent.getIntExtra("Num1",0);
        int num2 = InIntent.getIntExtra("Num2",0);
        String buho = InIntent.getStringExtra("buho");
        int result =0;

        if (buho.equals("+")){
            result = num1+num2;
        } else if (buho.equals("-")){
            result = num1-num2;
        }
        else if (buho.equals("*")){
            result = num1*num2;
        }
        else if (buho.equals("/")){
            result = num1/num2;
        }


        final int finalResult = result;

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent(getApplicationContext(), MainActivity.class);
                outIntent.putExtra("hap", finalResult);
                setResult(RESULT_OK, outIntent);
                finish();
            }
        });

    }
}
