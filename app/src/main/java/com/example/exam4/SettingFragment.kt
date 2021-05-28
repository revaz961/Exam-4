package com.example.exam4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.exam4.databinding.FragmentSettingBinding


class SettingFragment : Fragment() {

    private var binding:FragmentSettingBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(binding == null){
            binding = FragmentSettingBinding.inflate(inflater,container,false)
            init()
        }
        return binding!!.root
    }

    private fun init(){
        binding!!.btn3X3.setOnClickListener {
            setFragmentResult("GameMode", bundleOf("mode" to GameMode.ease))
            findNavController().navigateUp()
        }
        binding!!.btn4X4.setOnClickListener {
            setFragmentResult("GameMode", bundleOf("mode" to GameMode.medium))
            findNavController().navigateUp()
        }
        binding!!.btn5X5.setOnClickListener {
            setFragmentResult("GameMode", bundleOf("mode" to GameMode.hard))
            findNavController().navigateUp()
        }
    }
}