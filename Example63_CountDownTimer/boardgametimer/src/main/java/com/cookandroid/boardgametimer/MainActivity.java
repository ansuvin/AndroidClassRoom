package com.cookandroid.boardgametimer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //region 변수 선언
    EditText editTextEnteredSeconds;
    Button btnTimerSwitch, btnReset, btnPauseRestart;
    TextView textViewCopyRight;
    CountDownTimer countDownTimer;

    long fullTime, halfTime;
    boolean isPause = false;    //pause 상태인가?
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region 참조 변수에 UI 객체 연결
        editTextEnteredSeconds = findViewById(R.id.editTextEnteredSeconds);
        btnTimerSwitch = findViewById(R.id.btnTimerSwitch);
        btnReset = findViewById(R.id.btnReset);
        btnPauseRestart = findViewById(R.id.btnPauseRestart);
        textViewCopyRight = findViewById(R.id.textViewCopyright);
        //endregion

        //region btnTimerSwitch 클릭 이벤트
        btnTimerSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //벨소리 띵~
                MediaPlayer.create(getApplicationContext(), R.raw.bell_sound).start();

                cancelTimer();
                resetTimer();       //reset countDownTimer

                countDownTimer = countDownTimer(fullTime);
                countDownTimer.start();
            }
        });//endregion

        //region btnReset 버튼 클릭 이벤트
        btnReset.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                resetTimer();
                cancelTimer();

                btnTimerSwitch.setEnabled(true);
                return false;
            }
        });
        //endregion

        //region btnPauseRestart 버튼 클릭 이벤트
        btnPauseRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPause){
                    btnPauseRestart.setText(R.string.pause);
                    countDownTimer = countDownTimer(Long.parseLong(btnTimerSwitch.getText().toString()));
                    countDownTimer.start();
                    isPause = false;
                }else{
                    countDownTimer.cancel();    //타이머 멈추기
                    btnPauseRestart.setText(R.string.restart);
                    isPause = true;
                }
            }
        });
        //endregion
    }

    //기존에 실행되던 countDownTimer 멈추기
    private void cancelTimer() {
        if(countDownTimer != null)
            countDownTimer.cancel();
    }

    //CountDownTimer 생성 및 반환
    private CountDownTimer countDownTimer(long t){
        return new CountDownTimer(t*1000+1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long currentTime = millisUntilFinished / 1000;
                btnTimerSwitch.setText(String.valueOf(currentTime));
            }

            @Override
            public void onFinish() {
                btnTimerSwitch.setBackgroundColor(Color.DKGRAY);
                btnTimerSwitch.setTextColor(Color.GRAY);
                MediaPlayer.create(getApplicationContext(), R.raw.gameover_sound).start();
                btnTimerSwitch.setEnabled(false);   //버튼 비활성화
            }
        };
    }

    //타이머 리셋 메소드
    private void resetTimer() {
        fullTime = Long.parseLong(editTextEnteredSeconds.getText().toString());
        halfTime = Math.round(fullTime / 2);

        btnTimerSwitch.setBackgroundColor(Color.YELLOW);
        btnTimerSwitch.setTextColor(Color.BLACK);
        btnTimerSwitch.setEnabled(true);
        btnTimerSwitch.setText(String.valueOf(fullTime));
    }
}
