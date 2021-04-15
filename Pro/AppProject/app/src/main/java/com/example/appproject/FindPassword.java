package com.example.appproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class FindPassword extends AppCompatActivity implements View.OnClickListener{

    //define view objects
    private EditText editTextUserEmail;
    private Button buttonFind;
    private ProgressDialog progressDialog;
    //define firebase object
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);

        editTextUserEmail = findViewById(R.id.editTextUserEmail);
        buttonFind = findViewById(R.id.buttonFind);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        buttonFind.setOnClickListener(this);

    }

    //region 이메일 전송 버튼 클릭 이벤트
    @Override
    public void onClick(View view) {
        if(view == buttonFind){
            progressDialog.setMessage("처리중입니다. 잠시 기다려 주세요...");
            progressDialog.show();
            //비밀번호 재설정 이메일 보내기
            String emailAddress = editTextUserEmail.getText().toString().trim();
            firebaseAuth.sendPasswordResetEmail(emailAddress)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(FindPassword.this, "이메일을 보냈습니다.", Toast.LENGTH_LONG).show();
                                finish();
                            } else {
                                Toast.makeText(FindPassword.this, "메일 보내기 실패!", Toast.LENGTH_LONG).show();
                            }
                            progressDialog.dismiss();
                        }
                    });

        }
    }
    //endregion



}