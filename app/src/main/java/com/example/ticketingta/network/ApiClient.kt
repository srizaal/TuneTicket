package com.example.ticketingta.network

import com.example.ticketingta.model.LoginResponse
import com.example.ticketingta.model.RegistrasiResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiClient {
    @FormUrlEncoded
    @POST("login/login_service.php")
    fun  login(
        @Field("post_email") email : String,
        @Field("post_password") password : String
    ): Call<LoginResponse>

    fun  register(
        @Field("nama") nama : String,
        @Field("email") email : String,
        @Field("telepon") telepon : String,
        @Field("password") password : String
    ): Call<RegistrasiResponse>

}