package com.cookandroid.practice08_practice8_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;

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

        datePicker.init(2019, 0, 15, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                fileName = String.format("%d_%d_%d", year, monthOfYear, dayOfMonth);   //연_월_일.txt 파일 이름 생성
                String str = readDiary(fileName);       //선택된 날짜 이름을 가진 파일의 내용을 읽어와서 저장
                editText.setText(str);                  //읽어온 텍스트를 editText에 표시
                btnWriter.setEnabled(true);             //버튼활성화
            }
        });
    }

    private String readDiary(String fileName) {
        return null;
    }
}
