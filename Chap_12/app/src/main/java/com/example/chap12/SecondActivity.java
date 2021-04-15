package com.example.chap12;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class SecondActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        listView = findViewById(R.id.listView);
        String[] keys = ("title", "contents");
        int[] ids = {android.R.id.text1, android.R.id.text2};

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT title, contents from test_table", null);

        while(cursor.moveToNext()){
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("title",cursor.getString(0));
            hashMap.put("contents", cursor.getString(1));
            arrayList.add(hashMap);
        }
        db.close();

        SimpleAdapter adapter = new SimpleAdapter(this,
                arrayList,
                android.R.layout.simple_list_item_2,
                keys,
                ids);
    }
}