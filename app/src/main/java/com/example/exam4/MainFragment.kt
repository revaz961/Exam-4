package com.example.exam4

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.exam4.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private var binding:FragmentMainBinding? = null
    private var gameMode = GameMode.ease

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(binding == null){
            binding = FragmentMainBinding.inflate(inflater,container,false)
            init()
        }
        return binding!!.root
    }

    private fun init(){
        binding!!.btnSetting.setOnClickListener {
            this.findNavController().navigate(R.id.action_mainFragment_to_settingFragment)
        }

        binding!!.btnStart.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_gameFragment, bundleOf("mode" to gameMode))
        }

        setFragmentResultListener("GameMode") { key, bundle ->
            gameMode = bundle["mode"] as GameMode
        }
    }

}