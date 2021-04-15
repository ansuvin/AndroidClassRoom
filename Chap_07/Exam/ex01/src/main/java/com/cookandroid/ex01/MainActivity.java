package com.cookandroid.ex01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CancellationSignal;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.function.Consumer;

public class MainActivity extends AppCompatActivity {

    EditText num1, num2;
    Button btn_plu, btn_min, btn_mul, btn_div;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("2108 안수빈");

        num1= findViewById(R.id.editText);
        num2=findViewById(R.id.editText2);

        btn_div = findViewById(R.id.button4);
        btn_min = findViewById(R.id.button2);
        btn_mul = findViewById(R.id.button3);
        btn_plu = findViewById(R.id.button);

        textView = findViewById(R.id.textView);

        Button.OnClickListener listener = new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                int n1 = Integer.parseInt(num1.getText().toString());
                int n2 = Integer.parseInt(num2.getText().toString());
                switch (v.getId()){
                    case R.id.button:
                        textView.setText("계산 결과 : "+(n1+n2));
                        break;
                    case R.id.button2:
                        textView.setText("계산 결과 : "+(n1-n2));
                        break;
                    case R.id.button3:
                        textView.setText("계산 결과 : "+(n1*n2));
                        break;
                    case R.id.button4:
                        textView.setText("계산 결과 : "+(n1/n2));
                        break;
                }
            }
        };

        btn_plu.setOnClickListener(listener);
        btn_min.setOnClickListener(listener);
        btn_div.setOnClickListener(listener);
        btn_mul.setOnClickListener(listener);

    }
}
