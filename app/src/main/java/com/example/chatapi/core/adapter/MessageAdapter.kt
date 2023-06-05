package com.example.chatapi.core.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapi.core.data.ChatResponse
import com.example.chatapi.core.data.DataSender
import com.example.chatapi.databinding.ItemMessageBinding

class MessageAdapter: RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    private val data = ArrayList<ChatResponse>()
    var onItemClick : ((id:String)->Unit)?=null
    fun setData(data: List<ChatResponse>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }
    inner class MessageViewHolder(private val binding: ItemMessageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(data: ChatResponse) {
            binding.root.setOnClickListener {
                onItemClick?.invoke(data.id)
                DataSender.SMSUSER=data.text_message
                DataSender.USERSENDER=data.sender_name
            }
            binding.userMessage.setText(data.text_message)
            binding.userNameSender.setText(data.sender_name)
            if (data.sender_name.contains(DataSender.USERNAME)){
                binding.linerView.setBackgroundColor(Color.parseColor("#74D14D"))
            }else{
                binding.linerView.setBackgroundColor(Color.parseColor("#78C1E6"))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
            return MessageViewHolder(ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.setData(data = data[position])
    }
}