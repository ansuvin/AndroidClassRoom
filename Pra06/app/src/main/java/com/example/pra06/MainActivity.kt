package com.example.pra06

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    internal lateinit var preferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferences = getSharedPreferences("USERSIGN", MODE_PRIVATE)
        val editor = preferences!!.edit()


        //버튼을 클릭하면 입력한 이름을 쉐어드프리퍼런스에 내이름을 저장한다.
        //또한 그 이름을 가지고 채팅방으로 이동한다.

        val button : Button = findViewById(R.id.button);
        var editText : EditText = findViewById(R.id.editText);

        button.setOnClickListener{
            editor.putString("name", editText.text.toString())
            val intent = Intent(this, ChatRoomActivity::class.java)
            startActivity(intent)
        }

    }
}