package com.cookandroid.chap08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button btnWrite, btnRead;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        btnWrite = findViewById(R.id.btnWrite);
        btnRead = findViewById(R.id.btnRead);
        textView = findViewById(R.id.textView);

        //region 내장 메모리 파일 입출력 처리1 (FileOutputStream, FileInputStream) 영어만
        /*View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btnRead:
                        FileInputStream fis = null;
                        try (FileInputStream fis = new FileInputStream(getFileStreamPath(getFileStreamPath("myFile.txt"))){
                            //fis = new FileInputStream(getFileStreamPath("myFile.txt"));

                            //한바이트씩 모두 읽어오기
                            *//*String str = "";
                            int temp;
                            while((temp=fis.read()) != -1){
                                 str = (char)temp;

                            }
                            textView.setText(fis.read());*//*
                            byte[] bytesArray = new byte[20];
                            fis.read(bytesArray);
                            textView.setText(new String(bytesArray));

                        } catch (FileNotFoundException e){
                            e.printStackTrace();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                        break;
                    case R.id.btnWrite:
                        FileOutputStream fos = null;
                        try {
                            //파일 생성(없으면) 또는 연결(있으면
                            //fos = new FileOutputStream(getFileStreamPath("myFile.text"), true);
                            String string = editText.getText().toString();
                            fos.write(string.getBytes());

                            Toast.makeText(MainActivity.this, "myFile.txt에 문자열 써짐", Toast.LENGTH_SHORT).show();
                        }catch (IOException e){
                            e.printStackTrace();
                        }finally {
                            try{
                                fos.close();
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                        break;
                }
            }
        };
        */
        //endregion

        //region내장 메모리 파일 입출력 처리 2 (OutPutStreamWriter, InpPutStreamReader) 문자들
        //파일에서 바이트 단위로 읽은 자료를 문다 단위로 변환해주는 보조스트림
        View.OnClickListener listener2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btnWrite:
                        try(OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("myFile.txt", Context.MODE_PRIVATE))) {
                            outputStreamWriter.write(editText.getText().toString());
                            Toast.makeText(MainActivity.this, "myFile.txt 작성완료", Toast.LENGTH_SHORT).show();
                            editText.setText(null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case R.id.btnRead:
                        try(InputStreamReader inputStreamReader = new InputStreamReader(openFileInput("myFile.txt"))){
                            String str = "";
                            int temp;
                            while((temp = inputStreamReader.read()) != -1){
                                str += (char)temp;
                            }
                            textView.setText(str);
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                        break;
                }
            }
        };
        //endregion

        // 내장 메모리 파일 입출력 처리 3 (BufferedWritter, BufferedReader)

        btnWrite.setOnClickListener(listener2);
        btnRead.setOnClickListener(listener2);
    }
}
