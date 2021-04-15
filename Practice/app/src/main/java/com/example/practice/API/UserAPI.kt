package com.example.practice.API

import com.example.practice.DTO.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPI {
    @POST("/user/join")
    fun signUp(
        @Body signup : Signup
    ) : Call<Responsesign>

    @POST("/user/login")
    fun login(
        @Body login: Login
    ) : Call<Responselogin>


}