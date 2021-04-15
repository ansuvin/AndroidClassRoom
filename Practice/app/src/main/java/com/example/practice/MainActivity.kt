package com.example.practice

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.practice.API.RetrofitHelper
import com.example.practice.DTO.Login
import com.example.practice.DTO.Responselogin
import com.example.practice.DTO.Responsesign
import com.example.practice.DTO.Signup
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener {
            RetrofitHelper().getUserAPI().login(Login(editEmail.text.toString(), editPwd.text.toString()))
                    .enqueue(object : retrofit2.Callback<Responselogin> {
                        override fun onResponse(call: Call<Responselogin>, response: Response<Responselogin>) {
                            when(response.code()){
                                200 -> {
                                    val gson = GsonBuilder().create()
                                    val strData = gson.toJson(response.body()!!.userInfo, Responselogin::class.java)

                                    val sp = getSharedPreferences("data", Context.MODE_PRIVATE)
                                    val editor = sp.edit()
                                    editor.putString("data", strData)
                                    editor.apply()
                                    Toast.makeText(this@MainActivity,"로그인 되었습니다.", Toast.LENGTH_SHORT).show()
                                }
                                204 ->{
                                    Toast.makeText(this@MainActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                                }
                            }
                        }

                        override fun onFailure(call: Call<Responselogin>, t: Throwable) {
                            Toast.makeText(this@MainActivity, t.toString(), Toast.LENGTH_SHORT).show()
                        }

                    })
        }

        btnSignup.setOnClickListener {
            RetrofitHelper().getUserAPI().signUp(Signup(editName.text.toString(), editEmail.text.toString(),editPwd.text.toString(), editAge.text.toString(),"",editField.text.toString()))
                    .enqueue(object : Callback<Responsesign>{
                        override fun onResponse(call: Call<Responsesign>, response: Response<Responsesign>) {
                            when(response.code()){
                                200 -> {
                                    Toast.makeText(this@MainActivity,"회원가입 성공",Toast.LENGTH_SHORT).show()
                                }
                                403 -> {
                                    Toast.makeText(this@MainActivity, "아이디가 중복입니다.", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }

                        override fun onFailure(call: Call<Responsesign>, t: Throwable) {
                            Toast.makeText(this@MainActivity, "서버에러",Toast.LENGTH_SHORT).show()
                        }

                    })
        }
    }
}
