package com.example.ex02_handler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

/*
 * Main Thread에서 처리하는 코드중에 일정 작업을
 * 반복해서 처리해야 하는 경우가 있음
 * Handler을 사용해서 원하는 코드를 반복해서 작업
 *
 * Handler는 개발자가 만든 OS에 작업 수행을 요청하는 역할
 * 개발자가 작업을 요청하면, OS는 작업을 하지 않을 때
 * 개발자가 요청한 작업을 처리하게 됨, 이 처리는 Main Thread에서
 */

public class MainActivity extends AppCompatActivity {

    TextView text1, text2;

    Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.textView);
        text2 = findViewById(R.id.textView2);

        handler = new Handler();

        // handler 를 통해 요청할 작업
        ThreadClass threadClass = new ThreadClass();
        handler.postDelayed(threadClass,500);
    }

    public void btnMethod(View v) {
        // 현재 시간을 ms 단위로 가져오는 예제
        long now = System.currentTimeMillis();

        // 출력
        text1.setText("버튼 클릭에 의한 출력 "+ now);
    }

    class ThreadClass extends Thread {
        @Override
        public void run() {

            long now = System.currentTimeMillis();
            text2.setText("Handler 반영 시도: "+now);
            handler.postDelayed(this, 500);
        }
    }
}