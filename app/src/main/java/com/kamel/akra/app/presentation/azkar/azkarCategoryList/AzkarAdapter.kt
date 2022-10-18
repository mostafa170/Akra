package com.kamel.akra.app.presentation.azkar.azkarCategoryList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kamel.akra.databinding.ItemAzkarBinding
import com.kamel.akra.domain.entities.Zekr

class AzkarAdapter : ListAdapter<Zekr, AzkarAdapter.ViewHolder>(AzkarDiffCallback()){

    class ViewHolder(val binding: ItemAzkarBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            zekr: Zekr,
        ) {
            binding.zekr = zekr
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup):ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemAzkarBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

}


class AzkarDiffCallback : DiffUtil.ItemCallback<Zekr>() {
    override fun areItemsTheSame(oldItem: Zekr, newItem: Zekr): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Zekr, newItem: Zekr): Boolean {
        return oldItem.content == newItem.content
    }
}