package com.example.chatapi.ui.chat

import com.example.chatapi.core.data.ChatResponse

interface ChatContract {
    interface View{
        fun setData(data:List<ChatResponse>?)
        fun showError(error:String)
        fun setStateProgress(isVisible:Boolean)
        fun onSendMessage()
    }
    interface Presenter{
        fun loadAllData()
        fun sendMessage(sender_name:String,text_message:String)
    }
}