package com.cookandroid.example6_2_chronometer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {

    Button start,stop,reset;
    Chronometer time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.btn_start);
        stop =  findViewById(R.id.btn_stop);
        reset = findViewById(R.id.btn_reset);

        time = findViewById(R.id.chronometer);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_start:
                        time.setBase(SystemClock.elapsedRealtime());
                        time.start();
                        break;
                    case R.id.btn_stop:
                        time.stop();
                        break;
                    case R.id.btn_reset:
                        time.setBase(SystemClock.elapsedRealtime());
                        break;
                }
            }
        };
        start.setOnClickListener(listener);
        stop.setOnClickListener(listener);
        reset.setOnClickListener(listener);
    }
}
