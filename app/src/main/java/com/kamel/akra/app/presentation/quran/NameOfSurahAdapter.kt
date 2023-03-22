package com.kamel.akra.app.presentation.quran

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kamel.akra.databinding.ItemNameOfSurahBinding
import com.kamel.akra.domain.entities.Quran

class NameOfSurahAdapter (private val onNameOfSurahClickListener: OnNameOfSurahClickListener): ListAdapter<Quran, NameOfSurahAdapter.ViewHolder>(NameOfSurahDiffCallback()){

    class ViewHolder(val binding: ItemNameOfSurahBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            quran: Quran,
            onNameOfSurahClickListener: OnNameOfSurahClickListener
        ) {
            binding.nameOfSurah = quran
            binding.onClickListener = onNameOfSurahClickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup):ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemNameOfSurahBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position),onNameOfSurahClickListener)
}

class NameOfSurahDiffCallback : DiffUtil.ItemCallback<Quran>() {
    override fun areItemsTheSame(oldItem: Quran, newItem: Quran): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Quran, newItem: Quran): Boolean {
        return oldItem.id == newItem.id
    }
}

class OnNameOfSurahClickListener(val clickListener: (quran: Quran?) -> Unit) {
    fun onClick(quran: Quran?) = clickListener(quran)
}