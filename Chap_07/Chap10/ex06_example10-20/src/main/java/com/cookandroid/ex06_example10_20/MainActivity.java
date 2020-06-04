package com.cookandroid.ex06_example10_20;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnDial, btnWeb, btnGoogle, btnSearch, btnSms, btnPhoto, btnIntentTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDial = findViewById(R.id.btnDial);
        btnGoogle = findViewById(R.id.btnGoogle);
        btnPhoto = findViewById(R.id.btnPhoto);
        btnSearch = findViewById(R.id.btnSearch);
        btnSms = findViewById(R.id.btnSms);
        btnWeb = findViewById(R.id.btnWeb);
        btnIntentTest = findViewById(R.id.btnIntentFilter);

        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=null;
                switch (v.getId()){
                    case R.id.btnDial:
                        intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01012345678"));
                        break;
                    case R.id.btnWeb:
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.naver.com"));
                        break;
                    case R.id.btnGoogle:
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://goo.gl/maps/nEGqMqYgxBeeQWkN6"));
                        break;
                    case R.id.btnSearch:
                        intent = new Intent(Intent.ACTION_SEARCH);
                        intent.putExtra(SearchManager.QUERY, "여수 쫄보");
                        break;
                    case R.id.btnSms:
                        intent = new Intent(Intent.ACTION_SENDTO);
                        intent.putExtra("sms_body","안녕");
                        intent.setData(Uri.parse("smsto: "+Uri.encode("010-1234-5678")));
                        break;
                    case R.id.btnPhoto:
                        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        break;
                }
                startActivity(intent);
            }
        };

        btnDial.setOnClickListener(listener);
        btnWeb.setOnClickListener(listener);
        btnGoogle.setOnClickListener(listener);
        btnSearch.setOnClickListener(listener);
        btnSms.setOnClickListener(listener);
        btnPhoto.setOnClickListener(listener);

        //사용자가 설정한 암시적 인테트 필터 테스트
         btnIntentTest.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent();
                  intent.setAction("com.cookandroid.ex06_example10_20.ACTION_VIEW");
                  startActivity(intent);
              }
          }
         );

    }
}
