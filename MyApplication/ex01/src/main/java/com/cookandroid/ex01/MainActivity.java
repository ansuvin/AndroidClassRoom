package com.cookandroid.ex01;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText editText;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){
            switch (data.getIntExtra("radio",0)){
                case R.id.radioButton:
                    Uri uri = Uri.parse("tel:010-2467-504");
                    Intent intent = new Intent(Intent.ACTION_DIAL,uri);
                    startActivity(intent);
                    break;
                case R.id.radioButton2:
                    Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE);
                    startActivity(intent2);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("2108 안수빈");

        button = findViewById(R.id.button) ;
        editText = findViewById(R.id.editText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                intent.putExtra("value",editText.getText().toString());
                startActivityForResult(intent,0);
            }
        });


    }
}
