package com.cookandroid.ex02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText2);
        button = findViewById(R.id.button);

        final Intent intent = getIntent();

        String str = intent.getStringExtra("value");
        textView.setText(str);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("return",editText.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
