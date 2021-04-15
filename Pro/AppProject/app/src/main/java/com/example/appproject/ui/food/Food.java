package com.example.appproject.ui.food;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.appproject.R;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import kr.go.neis.api.Menu;
import kr.go.neis.api.NEISException;
import kr.go.neis.api.School;

public class Food extends Fragment {

    //region 변수 선언
    TextView breakfast, lunch, dinner, timeText, dayWeek;
    String[] dayOfWeek = {"일요일", "월요일", "화요일" , "수요일", "목요일", "금요일", "토요일"};
    Button btnAdd, btnSub;

    Calendar cal = Calendar.getInstance();
    //endregion

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_food, container, false); //프래그먼트 뷰 연결

        //region 변수 객체 연결
        cal.setTime(new Date());
        breakfast = root.findViewById(R.id.breakfast);
        lunch = root.findViewById(R.id.lunch);
        dinner = root.findViewById(R.id.dinner);
        timeText = root.findViewById(R.id.timeText);
        dayWeek = root.findViewById(R.id.day);
        btnAdd = root.findViewById(R.id.btnright);
        btnSub = root.findViewById(R.id.btnleft);
        //endregion

        makeText(); //급식 텍스트 띄우기

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal.add(Calendar.DATE, 1);
                makeText();
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal.add(Calendar.DATE, -1);
                makeText();
            }
        });

        return root;
    }

    //region 급식 텍스트 띄우는 메서드
    public void makeText(){
        final int year = cal.get(Calendar.YEAR);
        final int month = cal.get(Calendar.MONTH)+1;
        final int day = cal.get(Calendar.DATE)-1;
        int calDay = cal.get(Calendar.DAY_OF_WEEK);
        new Thread(){
            @Override
            public void run() {
                try {
                    School school = new School(School.Type.HIGH,School.Region.GWANGJU,"F100000120");

                    List<Menu> menu = school.getMonthlyMenu(year,month);
                    breakfast.setText(menu.get(day).getBreakfast());
                    if(breakfast.getText().toString().isEmpty()){
                        breakfast.setText("아침이 없습니다");
                    }
                    lunch.setText(menu.get(day).getLunch());
                    if(lunch.getText().toString().isEmpty()){
                        lunch.setText("점심이 없습니다");
                    }
                    dinner.setText(menu.get(day).getDinner());
                    if(dinner.getText().toString().isEmpty()){
                        dinner.setText("저녁이 없습니다");
                    }

                } catch (NEISException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        timeText.setText(year + "년 " + month + "월 " + (day+1) + "일");
        dayWeek.setText(dayOfWeek[calDay-1] + " 식단표");
    }
    //endregion

}