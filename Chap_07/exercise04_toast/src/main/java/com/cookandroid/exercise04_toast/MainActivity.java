package com.cookandroid.exercise04_toast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = new Toast(MainActivity.this);

                View toastView = View.inflate(MainActivity.this, R.layout.toast_layout, null);
                TextView toastTextView = toastView.findViewById(R.id.toastText);
                toastTextView.setText("내가 만든 토스트 메세지");

                toast.setView(toastView);
                toast.show();
            }
        });

    }
}
