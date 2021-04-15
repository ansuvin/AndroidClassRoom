package com.example.pra07_base64;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView;
        Button btn;
        imageView = findViewById(R.id.imageView);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.image1);

                Log.e("gg","g");
                bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
                Log.e("gg2","gg");

                byte[] bImage = baos.toByteArray();

                // import android.utill.Base64
                String base64 = Base64.encodeToString(bImage, Base64.DEFAULT);

                Log.e("gg2",base64);

                ByteArrayInputStream bais = new ByteArrayInputStream(bImage);
                Bitmap bitmap = BitmapFactory.decodeStream(bais);

                imageView.setImageBitmap(bitmap);
            }
        });
    }
}