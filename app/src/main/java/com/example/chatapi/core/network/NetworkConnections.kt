package com.example.chatapi.core.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkConnections {
    companion object{
        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://6479f21ea455e257fa641a06.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}