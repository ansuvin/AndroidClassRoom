package com.example.ex01_anr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/*
    * ANR(Application Not Respond) 에플리케이션이 응답하지 않는 경우
    Activity 처리하는데 사용되는 스레드 Main Thread = UI Thread
    Main Thread가 화면을 구성 완료하기 전에는 하얀 화면
    그 상태에서 작업이나 터치가 발생할 경우 ANR이 발생한다.

 */

public class MainActivity extends AppCompatActivity {

    TextView text1, text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.textView);
        text2 = findViewById(R.id.textView2);

        ThreadClass threadClass = new ThreadClass();
        threadClass.start();
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
            while (true) {
                SystemClock.sleep(500);
                long now = System.currentTimeMillis();
                // Log.d("test", "Thread에서의 작업: "+now);
                text2.setText("Thread에서 반영 시도: "+now);
            }
        }
    }
}