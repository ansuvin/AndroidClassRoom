package com.cookandroid.example6_3_countdowntimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button start,stop,pause;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        start = findViewById(R.id.btn_start);
        stop = findViewById(R.id.btn_stop);
        pause = findViewById(R.id.btn_pause);

        timer(30*1000,1000);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pause.getText().equals("PAUSE")){
                    pause.setText("RESTART");
                }else{
                    pause.setText("PAUSE");
                }
            }
        });

    }

    private void timer(long millisInFuture, long countDownInterval){
        timer = new CountDownTimer(millisInFuture,countDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText(String.valueOf(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "CountDown Finished", Toast.LENGTH_SHORT).show();
            }
        };
    }

}
