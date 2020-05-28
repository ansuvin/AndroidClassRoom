package com.cookandroid.ex02_practice10_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        btnReturn = findViewById(R.id.btnReturn);

        //MainActivity에서 보낸 인텐트 객체 받기, 보내온 데이터 저장
        Intent intent = getIntent();
        String[] imageNames = intent.getStringArrayExtra("nameData");
        int[] voteResult = intent.getIntArrayExtra("voteData");

        //화면의 TextView 참조변수 생성
        TextView[] textViews = new TextView[imageNames.length];
        RatingBar[] ratingBars = new RatingBar[voteResult.length];

        int[] textViewIDs = {R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4,
                R.id.tv5, R.id.tv6, R.id.tv7, R.id.tv8, R.id.tv9};
        int[] ratingBarIds = {R.id.rbar1, R.id.rbar2, R.id.rbar3, R.id.rbar4,
                R.id.rbar5, R.id.rbar6, R.id.rbar7, R.id.rbar8, R.id.rbar9};

        //region 참조변수에 View 객체 연결
        for (int i=0; i<textViews.length;i++){
            textViews[i] = findViewById(textViewIDs[i]);
            ratingBars[i] = findViewById(ratingBarIds[i]);
        };
        //endregion

        //TextView에 이미지 이름, RatingBar에 투표결과를 출력
        for(int i=0; i<voteResult.length;i++){
            textViews[i].setText(imageNames[i]);
            ratingBars[i].setRating(voteResult[i]);
        }

        //돌아가기 버튼 이벤트 처리
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();;
            }
        });

    }
}
