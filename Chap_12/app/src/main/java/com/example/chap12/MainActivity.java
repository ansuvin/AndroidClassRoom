package com.example.chap12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editTextTitle, editTextContents;
    Button btnAdd;

    DBHelper helper;    //테이블 생성, 스키마 변경 등의 작업을 도와주는 클래스
    SQLiteDatabase db;  //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextContents = findViewById(R.id.editContents);
        editTextTitle = findViewById(R.id.editTitle);
        btnAdd = findViewById(R.id.btnAdd);


        //클릭하면 editText 내용을 DB에 저장
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //입력된 문자열 가져오기
                String title = editTextTitle.getText().toString();
                String contents = editTextContents.getText().toString();

                try{
                helper = new DBHelper(MainActivity.this);
                db = helper.getWritableDatabase();
                String sql = String.format("INSERT INTO test_table (title, contents) VALUES ('%s, %s)", title, contents);
                db.execSQL(sql);}
                catch (Exception e){
                    Log.d("Database Error", e.toString());
                }finally {
                    db.close();
                }

                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}