package com.cookandroid.example6_1_textclock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView timeView;
    TextClock clock;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        timeView = findViewById(R.id.timeView);
        clock = findViewById(R.id.clock);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = clock.getText().toString();
                timeView.setText("Time"+str);
            }
        });
    }
}
