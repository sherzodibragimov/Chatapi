package com.example.chatapi.ui.chat

import com.example.chatapi.core.data.ChatRequest
import com.example.chatapi.core.data.ChatResponse
import com.example.chatapi.core.network.ChatApi
import com.example.chatapi.core.network.NetworkConnections
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatPresenter(val view: ChatContract.View):ChatContract.Presenter {

    private val chatApi = NetworkConnections
        .getRetrofit()
        .create(ChatApi::class.java)

    override fun loadAllData() {
        view.setStateProgress(isVisible = true)

        chatApi.loadAllMessage().enqueue(object : Callback<List<ChatResponse>> {
            override fun onResponse(call: Call<List<ChatResponse>>, response: Response<List<ChatResponse>>) {
                view.setData(response.body())
                view.setStateProgress(isVisible = false)
            }

            override fun onFailure(call: Call<List<ChatResponse>>, t: Throwable) {
                view.showError(error = t.message ?: t.toString())
                view.setStateProgress(isVisible = false)
            }
        })
    }

    override fun sendMessage(senderName: String, textMessage: String) {
        val request =ChatRequest(sender_name =senderName, text_message = textMessage)

        chatApi.sendMessage(request).enqueue(object : Callback<ChatResponse> {
            override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
                view.onSendMessage()
            }

            override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
                view.showError(error = t.message ?: t.toString())

            }

        })
    }


}