package com.example.practice.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper{

    private var retrofit = Retrofit.Builder()
        .baseUrl("http://b979efef3ef1.ngrok.io")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getUserAPI() : UserAPI{
        return retrofit.create(UserAPI::class.java)
    }

}