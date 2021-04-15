package com.cookandroid.practice04_permissions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button btnVIBRATE;
    TextView textView;

    //region 요청할 권한 목록
    String[] permissionList = {
           Manifest.permission.READ_EXTERNAL_STORAGE,
           Manifest.permission.WRITE_EXTERNAL_STORAGE,
           Manifest.permission.READ_CONTACTS,
           Manifest.permission.WRITE_CALENDAR,
           Manifest.permission.READ_SMS,
           Manifest.permission.SEND_SMS,
           Manifest.permission.ACCESS_FINE_LOCATION
    };
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnVIBRATE = findViewById(R.id.btnVibrate);
        textView = findViewById(R.id.textView);

        btnVIBRATE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);   //진동 울리는 객체 얻어오기
                vibrator.vibrate(2000);                                 //진동 2초동안 울리게하기
            }
        });

        /*if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            textView.setText("권한이 없음");
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},0);
        }else{
            textView.setText("권한이 있음");
        }*/

        //permissionList의 권한 요창
        ActivityCompat.requestPermissions(this,permissionList,100);
    }

    //권한 요청 후 자동 호출 메소드


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        String strGrantedList="", strDeniedList="";
        if(requestCode==100&&grantResults.length>0){
            for(int i=0;i<permissions.length;i++){
                if(grantResults[i] == PackageManager.PERMISSION_GRANTED)
                    strGrantedList += permissions[i]+'\n';
                else if(grantResults[i] == PackageManager.PERMISSION_DENIED)
                    strDeniedList += permissions[i]+'\n';
            }
        }
        textView.setText("혀용된 권한 목록\n"+strGrantedList+'\n');
        textView.append("거부된 권한 목록\n"+strDeniedList);
    }
}
