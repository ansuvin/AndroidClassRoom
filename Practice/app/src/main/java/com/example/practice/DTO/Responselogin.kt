package com.example.practice.DTO

data class Responselogin (
    val code : Int,
    val message : String,
    val userInfo : UserInfo
)