package com.example.ex01_service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyForegroundIntentService extends IntentService {

    public MyForegroundIntentService() {
        super("MyForegroundIntentService");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.d("test", "MyForegroundService 시작");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("test", "MyForegroundService 종료");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        for(int i=0; i<10; i++){
            SystemClock.sleep(1000);               // 1초 대기
            long time = System.currentTimeMillis();     //현재 시간 저장
            Log.d("test", "ForegroundService Running... "+ time);
        }
    }

}
