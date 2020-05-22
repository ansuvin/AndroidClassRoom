package com.cookandroid.practice08_practice8_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DatePicker datePicker;
    EditText editText;
    Button btnWriter;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 일기장");

        datePicker = findViewById(R.id.datePicker);
        editText = findViewById(R.id.editText);
        btnWriter = findViewById(R.id.btnWriter);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        readAndWriteDiary(year,month,day);

        datePicker.init(2019, 0, 15, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                readAndWriteDiary(year,monthOfYear,dayOfMonth);
            }
        });

        btnWriter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream outFs = openFileOutput(fileName, Context.MODE_PRIVATE);
                    String str = editText.getText().toString();
                    outFs.write(str.getBytes());
                    outFs.close();
                    Toast.makeText(getApplicationContext(), fileName+" 이 저장됨", Toast.LENGTH_SHORT).show();
                }catch (IOException e){

                }
            }
        });
    }

    private void readAndWriteDiary(int year, int month, int day) {
        fileName = String.format("%d_%d_%d", year, month+1, day);   //연_월_일.txt 파일 이름 생성
        String str = readDiary1(fileName);       //선택된 날짜 이름을 가진 파일의 내용을 읽어와서 저장
        editText.setText(str);                  //읽어온 텍스트를 editText에 표시
        btnWriter.setEnabled(true);             //버튼활성화
    }

    private String readDiary1(String fileName) {
        String diaryStr = null;
        FileInputStream inFs;

        try{
            inFs = openFileInput(fileName); //파일열기
            byte[] txt = new byte[500];     //byte형 배열 선언
            inFs.read(txt);                 //txt에 읽어들이기
            inFs.close();
            diaryStr = (new String(txt)).trim();    //trim() - 앞뒤의 공백을 제거
            btnWriter.setText("수정하기");
        }catch (IOException e){
            editText.setHint("일기 없음");
            btnWriter.setText("새로 저장");
        }
        return diaryStr;
    }


    //해당 날짜의 일기를 스트림 마지막까지 읽어오는 메소드
    private String readDiary2(String fileName) {
        String diaryStr = null;
        FileInputStream inFs;

        try{
            inFs = openFileInput(fileName); //파일열기
            byte[] txt = new byte[inFs.available()];     //읽어올 데이터 길이만큼의 배열을 생성
            inFs.read(txt);                 //txt에 읽어들이기
            inFs.close();
            diaryStr = (new String(txt)).trim();    //trim() - 앞뒤의 공백을 제거
            btnWriter.setText("수정하기");
        }catch (IOException e){
            editText.setHint("일기 없음");
            btnWriter.setText("새로 저장");
        }
        return diaryStr;
    }
}
