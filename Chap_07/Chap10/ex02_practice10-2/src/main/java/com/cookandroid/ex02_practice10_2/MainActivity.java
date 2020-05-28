package com.cookandroid.ex02_practice10_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //region 참조변수선언
    ImageView[] imageViews = new ImageView[9];  //ImageView를 연결할 참조 변수 배열
    Button btnResult;
    int[] imageIds = {R.id.iv1, R.id.iv2, R.id.iv3, R.id.iv4,   //각의 ImageView를 가리키는 di값 배열
            R.id.iv5, R.id.iv6, R.id.iv7, R.id.iv8, R.id.iv9};
    String[] imageNames ={"이미지1","이미지2","이미지3","이미지4",    //이미지들의 이름을 지정한 배열
            "이미지5","이미지6","이미지7","이미지8","이미지9"};
    int [] voteCount = new int[9];  //각 이미지의 클릭 횟수 지정 배열
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnResult = findViewById(R.id.btnFinish);

        // imageViews 배열의 xml의 ImageView 객체 연결
        for(int i=0; i<imageViews.length;i++){
            final int index = i;
            imageViews[i] = findViewById(imageIds[i]);
            imageViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    voteCount[index]++;     //이미지뷰가 ㅋ클릭된 횟수 저장
                    Toast.makeText(MainActivity.this, imageNames[index]+" : 총 "+voteCount[index]+"표", Toast.LENGTH_SHORT).show();
                }
            });
        }

        //[투표 종료] 버튼 이벤트 처리
        //이미지들의 이름과 투표 결과 배열을 ResultActivity로 전송
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);  //명시적 인텐트 객체 생성
                intent.putExtra("nameData",imageNames);
                intent.putExtra("voteData",voteCount);
                startActivity(intent);
            }
        });

    }
}
