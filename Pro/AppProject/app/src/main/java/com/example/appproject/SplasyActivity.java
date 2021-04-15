package com.example.appproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class SplasyActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            Thread.sleep(1500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }
}
