package com.cookandroid.practice10_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    Button btnNewActivity;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNewActivity = findViewById(R.id.btn);
        radioGroup = findViewById(R.id.radioGroup);

        btnNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.radio1:
                        Intent intent = new Intent(getApplicationContext(), secondActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.radio2:
                        Intent intent2 = new Intent(getApplicationContext(), thirdActivity.class);
                        startActivity(intent2);
                        break;
                }
            }
        });
    }
}
