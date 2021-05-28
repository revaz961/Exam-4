package com.example.exam4

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.exam4.databinding.RecycleItemBinding

class GameAdapter(
    private val items: MutableList<MutableList<CaseState>>,
    private val click: (Int,ImageButton) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val WITH_IMAGE = 1
        private const val WITHOUT_IMAGE = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView =
            RecycleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FirstViewHolder(itemView)

        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is FirstViewHolder -> holder.bind()
        }
    }

    override fun getItemCount() = items.size * items[0].size

    inner class FirstViewHolder(private val binding: RecycleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.root.setOnClickListener {
                click(adapterPosition,binding.btnBox)
            }
        }
    }
}