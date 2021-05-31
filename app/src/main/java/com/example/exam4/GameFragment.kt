package com.example.exam4

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
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
            var i = pos / items.size
            var j = pos % items.size
            if (firstPlayerTurn) {
                btnBox.setImageResource(R.drawable.outline_close_black_48)
                btnBox.scaleType = ImageView.ScaleType.CENTER
                items[i][j] = CaseState.x
                firstPlayerTurn = false
            } else {
                btnBox.setImageResource(R.drawable.outline_exposure_zero_black_48)
                btnBox.scaleType = ImageView.ScaleType.CENTER
                items[i][j] = CaseState.o
                firstPlayerTurn = true
            }

            btnBox.isClickable = false
            var winner = check()
            if (winner != CaseState.empty) {
                findNavController().navigate(
                    R.id.action_gameFragment_to_winFragment,
                    bundleOf("winner" to winner)
                )
            }
        }
        binding!!.rvGame.layoutManager = GridLayoutManager(requireContext(), items.size)
        binding!!.rvGame.adapter = adapter
    }

    private fun check(): CaseState {
        var win = true
        var winner = CaseState.empty

        items.forEach { list ->
            if (list[0] != CaseState.empty)
                if (list.all {
                        it == list[0]
                    })
                    return list[0]
        }

        for (i in 0 until items.size) {
            win = true
            winner = items[0][i]
            if (winner != CaseState.empty)
                for (j in 0 until items.size)
                    if (winner != items[j][i]) {
                        win = false
                        break
                    }
        }
        if (win && winner != CaseState.empty)
            return winner


        if (items[0][0] != CaseState.empty) {
            win = true
            for (i in 0 until items.size) {
                if (items[i][i] != items[0][0])
                    win = false
            }
            if (win)
                return items[0][0]
        }


        if (items[0][items.size - 1] != CaseState.empty) {
            win = true
            for (i in items.size - 1 downTo 0) {
                if (items[items.size - i - 1][i] != items[0][items.size - 1])
                    win = false
            }
            if (win)
                return items[0][items.size - 1]
        }

        if(items.all { row -> row.all { it != CaseState.empty } })
            findNavController().navigate(R.id.action_gameFragment_to_winFragment, bundleOf("winner" to CaseState.empty))

        return CaseState.empty
    }
}