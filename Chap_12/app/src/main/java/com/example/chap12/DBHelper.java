package com.example.chap12;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    public static  final int DATABASE_VERSION =1;

    public DBHelper(Context context){
        super(context, "testdb", null, DATABASE_VERSION);
    }


    //앱이 처음 실행될 때 처음 한 번만 호출되어 테이블 생성성
   @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE test_table ("+
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "title TEXT, "+
                "contents TEXT)";

        db.execSQL(sql);
       Log.d("SQLiteTest", "DBHelper onCreate() 메소드 실행");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
