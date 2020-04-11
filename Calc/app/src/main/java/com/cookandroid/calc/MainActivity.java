package com.cookandroid.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText Ed1,Ed2;
    Button add,min,mul,div;
    TextView resultText;
    Integer result;
    String str1,str2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Ed1 = (EditText)findViewById(R.id.num1);
        Ed2 = (EditText)findViewById(R.id.num2);

        add = (Button)findViewById(R.id.btn_add);
        min = (Button)findViewById(R.id.btn_min);
        mul = (Button)findViewById(R.id.btn_mul);
        div = (Button)findViewById(R.id.btn_div);

        resultText = (TextView)findViewById(R.id.result);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str1 = Ed1.getText().toString();
                str2 = Ed2.getText().toString();
                switch (v.getId()){
                    case R.id.btn_add:
                        result = Integer.parseInt(str1)+Integer.parseInt(str2);
                        resultText.setText("덧셈 계산 결과 : "+result);
                        break;
                    case R.id.btn_min:
                        result = Integer.parseInt(str1)-Integer.parseInt(str2);
                        resultText.setText("뺄셈 계산 결과 : "+result);
                        break;
                    case R.id.btn_mul:
                        result = Integer.parseInt(str1)*Integer.parseInt(str2);
                        resultText.setText("곱셈 계산 결과 : "+result);
                        break;
                    case R.id.btn_div:
                        result = Integer.parseInt(str1)/Integer.parseInt(str2);
                        resultText.setText("나눗셈 계산 결과 : "+result);
                        break;
                }
            }
        };

        add.setOnClickListener(listener);
        min.setOnClickListener(listener);
        mul.setOnClickListener(listener);
        div.setOnClickListener(listener);

    }
}
