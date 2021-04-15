package com.cookandroid.ex01_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    ProgressBar pb1, pb2;
    Button btnThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb1 = findViewById(R.id.progressBar1);
        pb2 = findViewById(R.id.progressBar2);
        btnThread = findViewById(R.id.btnThread);

        //region 1.잘못된 케이스
//        btnThread.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                for(int i =0;i<100;i++){
//                    pb1.setProgress(pb1.getProgress()+2);
//                    pb2.setProgress(pb2.getProgress()+1);
//                    SystemClock.sleep(100);     //0.1초 기다림
//                }
//            }
//        }); //endregion

        //region 2.Thread 클래스를 상속한 클래스의 인스턴스를 이용한 처리
        /*btnThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressThread pt1 = new ProgressThread(pb1,2);
                pt1.start();

                ProgressThread pt2 = new ProgressThread(pb2, 1);
                pt2.start();
            }
        });*/
        //endregion

        //region 3.익명의 Thread 객체 구현을 이용한 처리
        btnThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        for(int i=pb1.getProgress();i<=100;i++){
                            pb1.setProgress(pb1.getProgress()+2);
                            SystemClock.sleep(100);
                        }
                    }
                }.start();
                new Thread(){
                    @Override
                    public void run() {
                        for(int i=pb2.getProgress();i<=100;i++){
                            pb2.setProgress(pb2.getProgress()+1);
                            SystemClock.sleep(100);
                        }
                    }
                }.start();
            }
        });
        //endregion

        //region 4.Runable 인터페이스 구현을 이용한 처리
        btnThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=pb1.getProgress();i<=100;i++){
                    pb1.setProgress(pb1.getProgress()+2);
                    SystemClock.sleep(100);
                }
            }
        });
        //endregion
    }

    private class ProgressThread extends Thread{
        ProgressBar pb;
        int increaseValue;

        public ProgressThread(ProgressBar pb, int increaseValue){
            this.pb = pb;
            this.increaseValue = increaseValue;
        }

        @Override
        public void run() {
            //반복적으로 progressBar의 상태 증가시키기
            for(int i=pb.getProgress();i<=100;i++){
                pb.setProgress(pb.getProgress()+increaseValue);
                SystemClock.sleep(100);
            }
        }
    }
}
