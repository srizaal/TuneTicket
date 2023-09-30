package com.example.ticketingta.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
//    private const val BASE_URL = "http://192.168.0.105/" // Ganti dengan URL Anda

//    private val BASE_URL = "http://172.168.33.240/" // Ganti dengan URL Anda
    private val BASE_URL = "http://192.168.0.105/" // Ganti dengan URL Anda

    private fun getRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getInstance(): ApiClient {
        return getRetrofitClient().create(ApiClient::class.java)
    }
}