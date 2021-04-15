package com.cookandroid.ex01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    TextView textView ;
    RadioGroup radioGroup;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView= findViewById(R.id.textView);
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
                        setResult(RESULT_OK,intent);
                        finish();
                        break;
                    case R.id.radioButton2:
                        intent.putExtra("radio",R.id.radioButton2);
                        setResult(RESULT_OK,intent);
                        finish();
                        break;
                }
            }
        });
    }
}
