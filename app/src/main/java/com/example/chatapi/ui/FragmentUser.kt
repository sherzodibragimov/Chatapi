package com.example.chatapi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.chatapi.core.data.DataSender
import com.example.chatapi.databinding.FragmentUserDetailBinding

class FragmentUser: Fragment() {
    private val binding by lazy {
        FragmentUserDetailBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.usernameView.setText(DataSender.USERSENDER)
        binding.userSms.setText(DataSender.SMSUSER)
        binding.backUserDetail.setOnClickListener {
            val action= FragmentUserDirections.actionFragmentUserToFragmentChat()
            findNavController().navigate(action)
        }
    }
}