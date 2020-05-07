package com.cookandroid.exercise_dialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnDialog1, btnDialog2, btnDialog3, btnDialog4, btnDialog5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDialog1 = findViewById(R.id.btnDialog1);
        btnDialog2 = findViewById(R.id.btnDialog2);
        btnDialog3 = findViewById(R.id.btnDialog3);
        btnDialog4 = findViewById(R.id.btnDialog4);
        btnDialog5 = findViewById(R.id.btnDialog5);

        //region 기본대화상자 띄우기
        btnDialog1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("제목입니다.");
                builder.setMessage("내용입니다.");
                builder.setIcon(R.mipmap.ic_launcher);
                builder.show();
            }
        });
        //endregion


        //region 대화상자의 버튼 클릭 이벤트 처리 리스너 구현
        final DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_NEUTRAL:
                        Toast.makeText(MainActivity.this, "BUTTON_NUTRAL", Toast.LENGTH_SHORT).show();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        Toast.makeText(MainActivity.this, "BUTTON_NEGATIVE", Toast.LENGTH_SHORT).show();
                        break;
                    case DialogInterface.BUTTON_POSITIVE:
                        Toast.makeText(MainActivity.this, "BUTTOPN_POSITIVE", Toast.LENGTH_SHORT).show();
                }
            }
        };
        //endregion



        //region 버튼이 있는 대화상자 띄우기
        btnDialog2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("제목입니다.")
                        .setMessage("내용입니다.")
                        .setIcon(R.mipmap.ic_launcher)
                        .setCancelable(false)
                        .setNeutralButton("Neutral", null)
                        .setNegativeButton("Negative", null)
                        .setPositiveButton("Positive",dialogListener)
                        .show();
            }
        });
        //endregion

        //region 기본 목록 대화상자
        btnDialog3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //목록 데이터
                final String[] versionArray = new String[]{"누가","오레오","파이"};

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("좋아하는 버전은?")
                        .setIcon(R.mipmap.ic_launcher_round)
                        .setItems(versionArray, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, versionArray[which], Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("닫기",null)
                        .show();
            }
        });
        //endregion

        //region 라디오버튼 목록 대화상자
        btnDialog3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //목록 데이터
                final String[] versionArray = new String[]{"누가","오레오","파이"};

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("좋아하는 버전은?")
                        .setIcon(R.mipmap.ic_launcher_round)
                        .setSingleChoiceItems(versionArray, 1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, versionArray[which], Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("닫기",null)
                        .show();
            }
        });
        //endregion

        //region 라디오버튼 목록 대화상자
        btnDialog3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //목록 데이터
                final String[] versionArray = new String[]{"누가","오레오","파이"};
                boolean[] checkArray = new boolean[]{true,false,true};

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("좋아하는 버전은?")
                        .setIcon(R.mipmap.ic_launcher_round)
                        .setMultiChoiceItems(versionArray,null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if(isChecked)
                            Toast.makeText(MainActivity.this, versionArray[which], Toast.LENGTH_SHORT).show();
                    }
                })
                        .setPositiveButton("닫기",null)
                        .show();
            }
        });
        //endregion
    }
}
