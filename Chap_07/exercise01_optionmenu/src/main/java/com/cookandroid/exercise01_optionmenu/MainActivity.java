package com.cookandroid.exercise01_optionmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
    }

    //ctrl+O 엑티비티가 화면에 나타날 때, 메뉴를 생성하는 메소드
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //xml에서 menu객체로 만들어줄 Inflater 객체 생성
        MenuInflater menuInflater = getMenuInflater();

        //xml을 munu객체로 생성
        menuInflater.inflate(R.menu.option_menu, menu);

        //Java 코드에서 메뉴 아이템 추가
        menu.add(Menu.NONE, Menu.FIRST, Menu.NONE,"Java 코드에서 추가한 메뉴");
        menu.add(Menu.NONE, Menu.FIRST+1, Menu.NONE,"Java 코드에서 추가한 메뉴2");
        return true;
    }

    //옵션 메뉴가 선택되면 호출되는 메소드
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                textView.setText("메뉴1 선택됨");
                break;
            case R.id.item2:
                textView.setText("메뉴2 선택됨");
                break;
            /*case R.id.item3:
                textView.setText("메뉴3 선택됨");
                break;*/
            case R.id.subItem1:
                textView.setText("서브메뉴1 선택됨");
                break;
            case R.id.subItem2:
                textView.setText("서브메뉴2 선택됨");
                break;
            case Menu.FIRST:
                textView.setText("Java 코드로 추가한 아이템 선택됨");
                break;
            case Menu.FIRST+1:
                textView.setText("Java 코드로 추가한 아이템2 선택됨");
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
