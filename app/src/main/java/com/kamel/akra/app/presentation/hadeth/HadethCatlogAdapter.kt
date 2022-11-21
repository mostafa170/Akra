package com.kamel.akra.app.presentation.hadeth

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kamel.akra.databinding.ItemHadethCatlogBinding
import com.kamel.akra.domain.entities.HadethCategories

class HadethCatlogAdapter (private val onHadethCategoriesClickListener: OnHadethCategoriesClickListener): ListAdapter<HadethCategories, HadethCatlogAdapter.ViewHolder>(HadethDiffCallback()){

    class ViewHolder(val binding: ItemHadethCatlogBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            hadethCategories: HadethCategories,
            onHadethCategoriesClickListener: OnHadethCategoriesClickListener
        ) {
            binding.hadeth = hadethCategories
            binding.onClickListener = onHadethCategoriesClickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup):ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemHadethCatlogBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position),onHadethCategoriesClickListener)

}

class HadethDiffCallback : DiffUtil.ItemCallback<HadethCategories>() {
    override fun areItemsTheSame(oldItem: HadethCategories, newItem: HadethCategories): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: HadethCategories, newItem: HadethCategories): Boolean {
        return oldItem.id == newItem.id
    }
}

class OnHadethCategoriesClickListener(val clickListener: (hadethCategories: HadethCategories?) -> Unit) {
    fun onClick(hadethCategories: HadethCategories?) = clickListener(hadethCategories)
}