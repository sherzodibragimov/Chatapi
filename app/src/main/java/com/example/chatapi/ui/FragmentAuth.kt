package com.example.chatapi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.chatapi.core.data.DataSender
import com.example.chatapi.databinding.FragmentAuthBinding

class FragmentAuth : Fragment() {
  private val binding by lazy {
    FragmentAuthBinding.inflate(layoutInflater)
  }
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return binding.root
  }
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.startButton.setOnClickListener{
      if (!binding.nameUser.text.toString().isEmpty()){
        val action = FragmentAuthDirections.actionFragmentAuthToFragmentChat()
        findNavController().navigate(action)
        DataSender.USERNAME=binding.nameUser.text.toString()
      }
    }
  }

}