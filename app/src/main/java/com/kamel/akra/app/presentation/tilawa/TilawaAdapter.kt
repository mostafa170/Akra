package com.kamel.akra.app.presentation.tilawa

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kamel.akra.databinding.ItemTilawaAudioBinding
import com.kamel.akra.domain.entities.QuranAudio

class TilawaAdapter (private val onSurahAudioClickListener: OnSurahAudioClickListener): ListAdapter<QuranAudio, TilawaAdapter.ViewHolder>(
    SurahAudioDiffCallback()
){

    class ViewHolder(val binding: ItemTilawaAudioBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            quran: QuranAudio,
            onSurahAudioClickListener: OnSurahAudioClickListener
        ) {
            binding.nameOfSurah = quran
            binding.onClickListener = onSurahAudioClickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup):ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemTilawaAudioBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position),onSurahAudioClickListener)
}

class SurahAudioDiffCallback : DiffUtil.ItemCallback<QuranAudio>() {
    override fun areItemsTheSame(oldItem: QuranAudio, newItem: QuranAudio): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: QuranAudio, newItem: QuranAudio): Boolean {
        return oldItem.soraName == newItem.soraName
    }
}

class OnSurahAudioClickListener(val clickListener: (quran: QuranAudio?) -> Unit) {
    fun onClick(quran: QuranAudio?) = clickListener(quran)
}