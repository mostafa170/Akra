package com.kamel.akra.app.presentation.prayer

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kamel.akra.data.utils.*
import com.kamel.akra.databinding.ItemPrayerBinding
import com.kamel.akra.domain.entities.Prayer

class PrayersListAdapter(private val resources: Resources): ListAdapter<Prayer, PrayersListAdapter.PrayerViewHolder>(DiffCallback()) {


    class PrayerViewHolder(private val binding: ItemPrayerBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(
            prayer: Prayer,
            resources: Resources,
            showMuteIcon: Boolean,
            prayerOnNotifyChangeListener: PrayerOnNotifyChangeListener
        ){
            binding.prayerImage = prayer.id.toPrayerImage()
            binding.prayerName = prayer.id.toPrayerName(resources, false)
            binding.prayerTime = prayer.dateTime.toPrayerTime()
            binding.showMuteIcon = showMuteIcon
            binding.isPayerNotificationEnabled = SharedPreferencesData.isNotifyPrayer(prayer.id.toPrayerIdName())
            binding.prayer = prayer
            binding.executePendingBindings()

            binding.imageViewNotification.setOnClickListener {
                SharedPreferencesData.setNotifyPrayer(prayer.id.toPrayerIdName(), !SharedPreferencesData.isNotifyPrayer(prayer.id.toPrayerIdName()))
                prayerOnNotifyChangeListener.clickListener()
            }
        }

        companion object {
            fun from(parent: ViewGroup): PrayerViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemPrayerBinding.inflate(layoutInflater, parent, false)
                return PrayerViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrayerViewHolder {
        return PrayerViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PrayerViewHolder, position: Int) {
        holder.bind(getItem(position),  resources, position != 1,  PrayerOnNotifyChangeListener {
            notifyDataSetChanged()
        })
    }
}

class PrayerOnNotifyChangeListener(val clickListener: () -> Unit){
    fun onClick() = clickListener()
}

class DiffCallback : DiffUtil.ItemCallback<Prayer>() {
    override fun areItemsTheSame(oldItem: Prayer, newItem: Prayer): Boolean {
        return oldItem === newItem
    }
    override fun areContentsTheSame(oldItem: Prayer, newItem: Prayer): Boolean {
        return oldItem.id == newItem.id
    }
}
