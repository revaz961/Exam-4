package com.example.exam4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.exam4.databinding.FragmentWinBinding

class WinFragment : Fragment() {

    private var binding: FragmentWinBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentWinBinding.inflate(inflater, container, false)
            init()
        }
        return binding!!.root
    }

    private fun init() {
        var winner = arguments?.get("winner") as CaseState
        if (winner == CaseState.empty)
            binding!!.tvWinner.text = "draw"
        else
            binding!!.tvWinner.text = "WIN: PLAYER: ${winner.toString().uppercase()}"

        binding!!.btnReturn.setOnClickListener {
            findNavController().navigate(R.id.action_winFragment_to_mainFragment)
        }
    }
}