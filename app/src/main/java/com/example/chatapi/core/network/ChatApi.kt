package com.example.chatapi.core.network

import com.example.chatapi.core.data.ChatRequest
import com.example.chatapi.core.data.ChatResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ChatApi {
    @GET("/messages")
    fun loadAllMessage(): Call<List<ChatResponse>>

    @POST("/messages")
    fun sendMessage(@Body data: ChatRequest): Call<ChatResponse>
}