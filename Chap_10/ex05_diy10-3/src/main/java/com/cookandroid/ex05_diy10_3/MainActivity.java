package com.cookandroid.ex05_diy10_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editText1, editText2;
    Button btnCalculate;
    RadioGroup radioGroup;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode == RESULT_OK){
           int hap = data.getIntExtra("hap",0);
            Toast.makeText(this, "합계 : "+hap, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.editTextNum1);
        editText2 = findViewById(R.id.editTextNum2);
        btnCalculate = findViewById(R.id.result);
        radioGroup = findViewById(R.id.radioGroup);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), secondActivity.class);
                intent.putExtra("Num1", Integer.parseInt(editText1.getText().toString()));
                intent.putExtra("Num2", Integer.parseInt(editText2.getText().toString()));

                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.btn_plu:
                        intent.putExtra("buho","+");
                        break;
                    case R.id.btn_min:
                        intent.putExtra("buho","-");
                        break;
                    case R.id.btn_mul:
                        intent.putExtra("buho","*");
                        break;
                    case R.id.btn_div:
                        intent.putExtra("buho","/");
                        break;
                }
                startActivityForResult(intent,0);
            }
        });




    }
}
