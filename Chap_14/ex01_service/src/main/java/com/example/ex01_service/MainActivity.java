package com.example.ex01_service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnStartService, btnStopService, btnStartIntentService, btnStartForegroundIntentService;
    Intent service_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartService = findViewById(R.id.btnStartService);
        btnStopService = findViewById(R.id.btnStopService);
        btnStartIntentService = findViewById(R.id.btnStartIntentService);
        btnStartForegroundIntentService = findViewById(R.id.btnForegroundStartIntentService);

        btnStopService.setOnClickListener(this);
        btnStartService.setOnClickListener(this);
        btnStartIntentService.setOnClickListener(this);
        btnStartForegroundIntentService.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnStartService:      //서비스 시작
                service_intent = new Intent(this, MyService.class);
                startService(service_intent);
                break;
            case R.id.btnStopService:           // 서비스 종료
                stopService(service_intent);
                break;
            case R.id.btnStartIntentService:    // 인텐트 서비스 시작
                service_intent = new Intent(this, MyIntentService.class);
                startService(service_intent);
                break;
            case R.id.btnForegroundStartIntentService:      //Foreground 인텐트 서비스 시작
                service_intent = new Intent(this, MyForegroundIntentService.class);
                startForegroundService(service_intent);
        }
    }
}
