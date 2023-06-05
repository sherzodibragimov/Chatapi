package com.example.chatapi.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapi.core.adapter.MessageAdapter
import com.example.chatapi.core.data.ChatResponse
import com.example.chatapi.core.data.DataSender
import com.example.chatapi.databinding.FragmentChatBinding

class FragmentChat:Fragment(),ChatContract.View {
    private val presenter by lazy {
        ChatPresenter(this)
    }

    private val adapter = MessageAdapter()

    private val binding by lazy {
        FragmentChatBinding.inflate(layoutInflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }



    override fun onStart() {
        super.onStart()

        binding.chatList.adapter=adapter
        binding.chatList.layoutManager = LinearLayoutManager(requireContext())

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.loadAllData()

        binding.swipeLayout.setOnRefreshListener {
            presenter.loadAllData()
        }
        binding.sendButton.setOnClickListener {
            presenter.sendMessage(DataSender.USERNAME,binding.messageSendText.text.toString())
            binding.messageSendText.setText("")
           presenter.loadAllData()
        }


        adapter.onItemClick={id->
            val action = FragmentChatDirections.actionFragmentChatToFragmentUser()
            findNavController().navigate(action)
        }

    }

    override fun setData(data: List<ChatResponse>?) {
        data?.let {
            adapter.setData(data = it)
        }
    }

    override fun showError(error: String) {

        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()

    }

    override fun setStateProgress(isVisible: Boolean) {

        binding.swipeLayout.isRefreshing = isVisible

    }

    override fun onSendMessage() {

    }
}