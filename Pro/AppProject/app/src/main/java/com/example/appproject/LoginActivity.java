package com.example.appproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    //region 변수 선언
    Button btnLogin, btnSearchPassword;
    private FirebaseAuth mAuth;
    EditText edEmail, edPassword;
    String email, password;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.login);
        btnSearchPassword = findViewById(R.id.findPassword);
        edEmail = findViewById(R.id.inputEmail);
        edPassword = findViewById(R.id.inputPassword);


        //region 전에 로그인 한적이 있으먄 자동 로그인
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            moveMainPage(mAuth.getCurrentUser());
        }
        //endregion

        //region 로그인 이벤트 처리
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edEmail.getText().toString();
                password = edPassword.getText().toString();
                if (edEmail.getText().toString().isEmpty() || edPassword.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "이메일과 비밀번호를 입력하세요!", Toast.LENGTH_LONG).show();
                } else {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        moveMainPage(user);
                                    } else {
                                        Toast.makeText(LoginActivity.this, "이메일이나 비밀번호가 올바르지 않습니다", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
        });
        //endregion

        btnSearchPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, FindPassword.class);
                startActivity(intent);
            }
        });
    }

    //region 로그인 성공시 메인으로 이동
    private void moveMainPage(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    //endregion
}