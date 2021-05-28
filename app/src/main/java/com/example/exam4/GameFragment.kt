package com.example.exam4

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exam4.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private var binding: FragmentGameBinding? = null
    private var adapter: GameAdapter? = null
    private var items = mutableListOf<MutableList<CaseState>>()
    private var mode: GameMode = GameMode.ease
    private var firstPlayerTurn = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentGameBinding.inflate(inflater, container, false)
            init()
        }
        return binding!!.root
    }

    private fun init() {
        mode = arguments?.get("mode") as GameMode
        when (mode) {
            GameMode.ease -> {
                for (i in 0..2) {
                    items.add(mutableListOf<CaseState>())
                    for (j in 0..2)
                        items[i].add(CaseState.empty)
                }
            }
            GameMode.medium -> {
                for (i in 0..3) {
                    items.add(mutableListOf<CaseState>())
                    for (j in 0..3)
                        items[i].add(CaseState.empty)
                }
            }
            GameMode.hard -> {
                for (i in 0..4) {
                    items.add(mutableListOf<CaseState>())
                    for (j in 0..4)
                        items[i].add(CaseState.empty)
                }
            }
        }

        adapter = GameAdapter(items) { pos, btnBox ->
            if (firstPlayerTurn) {
                btnBox.setBackgroundResource(R.drawable.outline_close_24)
                firstPlayerTurn = false
            }
            else {
                btnBox.setImageResource(R.drawable.outline_exposure_zero_24)
                firstPlayerTurn = true
            }

            btnBox.isClickable = false

            d("position", pos.toString())
        }
        binding!!.rvGame.layoutManager = GridLayoutManager(requireContext(), items.size)
        binding!!.rvGame.adapter = adapter
    }

    private fun check() {
        var win = false
        items.forEach{ pos ->
            if(pos.all{
                it == CaseState.x
                })
                    findNavController().navigate()
        }
    }

}