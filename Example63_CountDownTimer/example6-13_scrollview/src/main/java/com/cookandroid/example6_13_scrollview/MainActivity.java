package com.cookandroid.example6_13_scrollview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayout = findViewById(R.id.linearLayout1);

        for (int i = 0; i<100; i++){
            Button button = new Button(this);
            button.setTextSize(30);
            button.setText("버튼"+i);
            linearLayout.addView(button);
        }

    }
}
