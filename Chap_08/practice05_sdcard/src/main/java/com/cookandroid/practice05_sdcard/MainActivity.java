package com.cookandroid.practice05_sdcard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btnRead, btnWrite;
    EditText editText;
    TextView textView;

    // 권한 목록 배열
    String[] permissionList={
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    // 외부 저장소에 읽기/쓰기할 디렉토리 참조 벼수 생성
    File appSpecificExternalDir ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRead = findViewById(R.id.btnRead);
        btnWrite = findViewById(R.id.btnWrite);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        // 권한 체크
        checkPermissions();

        //외부 저장장치와 사용 가능 여부 체크
        if (isExternalStroageWritable()){
            Toast.makeText(this, "외부 저장소 쓰기 가능", Toast.LENGTH_SHORT).show();
        }
        if (isExternalStroageReadable()){
            Toast.makeText(this, "외부 저장소 읽기 가능", Toast.LENGTH_SHORT).show();
        }

        //region 외부 저장소의 앱별 디렉토리 접근 getExternalFilesDir()
        File[] externalStorageVolumes = ContextCompat.getExternalFilesDirs(this,null);
        File primaryExternalStorage = externalStorageVolumes[0];    //기본 외부 저장장치
        Log.d("MainActivity",String.valueOf(primaryExternalStorage.isFile()));          //false
        Log.d("MainActivity",String.valueOf(primaryExternalStorage.isDirectory()));     //true
        Log.d("MainActivity",primaryExternalStorage.getName());                         //false
        Log.d("MainActivity",primaryExternalStorage.getAbsolutePath());                 //
        Log.d("MainActivity",primaryExternalStorage.getPath());
        Log.d("MainActivity",String.valueOf(primaryExternalStorage.canRead()));         //true
        Log.d("MainActivity",String.valueOf(primaryExternalStorage.canWrite()));        //true
        //endregion

        //region 외부 저장소 앱별 비공개 디렉토리에 파일 작성
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appSpecificExternalDir = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),"myDocuments");
                Log.d("MainActivity",appSpecificExternalDir.getAbsolutePath());

                // appSpecificExternalDir 실제 디렉토리 생성
                if(appSpecificExternalDir.mkdir()==false){
                    Log.d("MainActiviry","myDocuments 디렉토리 생성 실패") ;
                }

                // 실제 텍스트 파일 생성 및 텍스트 쓰기
                File myDocumentsFile1 = new File(appSpecificExternalDir,"document1.txt");
                try (FileOutputStream fos = new FileOutputStream(appSpecificExternalDir.getAbsoluteFile() + "document01.txt", false)){
                    fos.write(editText.getText().toString().getBytes());    //editText의 입력갑을 파일에 쓰기
                    editText.setText("");
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        });
        //endregion

        /*//region 외부 저장소 앱별 비공개 디렉토리에서 파일 읽기 -바이트 단위 읽기
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File readFile = new File(appSpecificExternalDir,"document1.txt");
                try(FileInputStream fis = new FileInputStream(readFile)){
                    int i;
                    StringBuilder stringBuilder= new StringBuilder();
                    while((i=fis.read())!= -1){
                        stringBuilder.append((char)i);
                    }
                    textView.setText(stringBuilder.toString());
                } catch(IOException e){
                    e.printStackTrace();
                }

            }
        });*/

        //region 외부 저장소 앱별 비공개 디렉토리에서 파일 읽기 -문자 단위 읽기
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File readFile = new File(appSpecificExternalDir,"document1.txt");
                try(FileReader fis = new FileReader(readFile)){
                    int i;
                    StringBuilder stringBuilder= new StringBuilder();
                    while((i=fis.read())!= -1){
                        stringBuilder.append((char)i);
                    }
                    textView.setText(stringBuilder.toString());
                } catch(IOException e){
                    e.printStackTrace();
                }

            }
        });
    }

    // Checks if external storage is available for read and write
    private boolean isExternalStroageReadable() {
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)){
            return true;
        }
        return false;
    }

    // Checks if external storage is available to at least read
    private boolean isExternalStroageWritable() {
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)){
            return true;
        }
        return false;
    }

    private void checkPermissions() {
        for(String permission : permissionList){
            if (ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, permission+" 권한없음", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this,permissionList,0);
            }else{
                Toast.makeText(this, permission+" 권한이있음", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
