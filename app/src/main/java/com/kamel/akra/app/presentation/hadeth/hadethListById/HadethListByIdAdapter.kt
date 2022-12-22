package com.kamel.akra.app.presentation.hadeth.hadethListById

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kamel.akra.databinding.ItemHadethListByIdBinding
import com.kamel.akra.domain.entities.HadethCategories

class HadethListByIdAdapter (private val onHadethListByIdClickListener: OnHadethListByIdClickListener): ListAdapter<HadethCategories, HadethListByIdAdapter.ViewHolder>(
    HadethListByIdDiffCallback()
){
    class ViewHolder(val binding: ItemHadethListByIdBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            hadethCategories: HadethCategories,
            onHadethListByIdClickListener: OnHadethListByIdClickListener
        ) {
            binding.hadeth = hadethCategories
            binding.onClickListener = onHadethListByIdClickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup):ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemHadethListByIdBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position),onHadethListByIdClickListener)
}

class HadethListByIdDiffCallback : DiffUtil.ItemCallback<HadethCategories>() {
    override fun areItemsTheSame(oldItem: HadethCategories, newItem: HadethCategories): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: HadethCategories, newItem: HadethCategories): Boolean {
        return oldItem.id == newItem.id
    }
}

class OnHadethListByIdClickListener(val clickListener: (hadethCategories: HadethCategories?) -> Unit) {
    fun onClick(hadethCategories: HadethCategories?) = clickListener(hadethCategories)
}