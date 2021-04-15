package com.example.ehddkfl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout linearLayout;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        linearLayout = findViewById(R.id.linearLayout);
        linearLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this, "클릭", Toast.LENGTH_SHORT).show();
    }
}