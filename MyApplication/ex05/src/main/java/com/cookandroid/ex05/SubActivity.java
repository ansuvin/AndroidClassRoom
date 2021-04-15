package com.cookandroid.ex05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    TextView textView;
    RadioGroup radioGroup;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        textView = findViewById(R.id.textView);
        radioGroup = findViewById(R.id.radioGroup);
        button = findViewById(R.id.button2);

        final Intent intent = getIntent();
        textView.setText(intent.getStringExtra("value"));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.radioButton:
                        intent.putExtra("radio",R.id.radioButton);
                        break;
                    case R.id.radioButton2:
                        intent.putExtra("radio",R.id.radioButton2);
                        break;
                }
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
