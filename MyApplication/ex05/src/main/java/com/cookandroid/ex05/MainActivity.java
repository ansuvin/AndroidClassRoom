package com.cookandroid.ex05;

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

    EditText editText;
    Button button;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            switch (data.getIntExtra("radio",0)){
                case R.id.radioButton:
                    Uri uri = Uri.parse("tel0629520856");
                    Intent intent1 = new Intent(Intent.ACTION_DIAL,uri);
                    startActivity(intent1);
                    break;
                case R.id.radioButton2:
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE);
                    startActivity(intent);
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("2108안수빈");

        editText = findViewById(R.id.editText);
        button =findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SubActivity.class);
                intent.putExtra("value",editText.getText().toString());
                startActivityForResult(intent,0);
            }
        });
    }
}
