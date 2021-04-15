package com.cookandroid.test1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView textView;
    Button btnAdd, btnEdit, btnDel;
    ListView listView;
    ArrayAdapter adapter;

    ArrayList<String> dataSet = getList(5);

    private ArrayList<String> getList(int cnt) {
        ArrayList<String> list = new ArrayList<>();
        for(int i=0;i<cnt;i++){
            list.add("리스트 데이터"+(i+1));
        }
        return list;
    }

    int chItem =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("2108안수빈");

        textView = findViewById(R.id.textView);
        btnAdd = findViewById(R.id.btnAdd);
        btnDel = findViewById(R.id.btnDel);
        btnEdit = findViewById(R.id.btnEdit);
        listView = findViewById(R.id.listView);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice,dataSet);

        listView.setAdapter(adapter);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(dataSet.get(position));
                chItem = position;
            }
        });

        btnEdit.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAdd:
                int cnt = listView.getCount();
                dataSet.add("리스트 데이터"+(cnt+1));
                break;
            case R.id.btnEdit:
                final EditText editText = new EditText(getApplicationContext());
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("리스트 아이템 수정")
                        .setMessage("현재 데이터: "+dataSet.get(chItem))
                        .setIcon(R.mipmap.ic_launcher_round)
                        .setView(editText)
                        .setCancelable(false)
                        .setPositiveButton("수정", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dataSet.set(chItem, editText.getText().toString());
                            }
                        })
                        .setNegativeButton("취소", null)
                        .show();
                break;
            case R.id.btnDel:
                dataSet.remove(chItem);
                break;
        }
        listView.clearChoices();
        adapter.notifyDataSetChanged();
    }
}
