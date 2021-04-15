package com.example.pra04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;

    Button btnSend;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences("USERSIGN", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editText = findViewById(R.id.editText);
        btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("name", editText.getText().toString());
                editor.commit();
                Intent intent = new Intent(getApplicationContext(), ChatRoomActivity.class);
                startActivity(intent);
            }
        });
    }
}