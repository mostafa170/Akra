package com.kamel.akra.app.presentation.onBoard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kamel.akra.databinding.ItemIntroBinding

class OnBoardPagerAdapter (): ListAdapter<IntroItem, OnBoardPagerAdapter.ViewHolder>(PagerListDiffCallback()) {

    class ViewHolder(private val binding: ItemIntroBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: IntroItem,
        ) {
            binding.item = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemIntroBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =  holder.bind(getItem(position))
}

class PagerListDiffCallback : DiffUtil.ItemCallback<IntroItem>() {
    override fun areItemsTheSame(oldItem: IntroItem, newItem: IntroItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: IntroItem, newItem: IntroItem): Boolean {
        return oldItem == newItem
    }
}