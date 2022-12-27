package com.kamel.akra.data.utils

import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kamel.akra.R
import com.kamel.akra.app.presentation.azkar.azkarCategoryList.AzkarAdapter
import com.kamel.akra.app.presentation.hadeth.HadethCatlogAdapter
import com.kamel.akra.app.presentation.prayer.PrayersListAdapter
import com.kamel.akra.domain.entities.HadethCategories
import com.kamel.akra.domain.entities.Prayer
import com.kamel.akra.domain.entities.Zekr

@BindingAdapter("myImageResource")
fun bindImageViewResourceId(imageView: ImageView, resourceId: Int) {
    imageView.setImageResource(resourceId)
}



@BindingAdapter("app:tint")
fun ImageView.setImageTint(@ColorInt color: Int) {
    setColorFilter(color)
}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    Glide.with(view.context).load(url)
        .error(R.drawable.ic_no_image)
        .into(view)
}

@BindingAdapter("azkarItems")
fun bindAzkarItemsRecyclerView(recyclerView: RecyclerView, data: List<Zekr>?) {
    recyclerView.adapter?.let {
        val adapter = it as AzkarAdapter
        adapter.submitList(data)
    }
}

@BindingAdapter("hadethCategoriesItems")
fun bindHadethCatlogListItemsRecyclerView(recyclerView: RecyclerView, data: List<HadethCategories>?) {
    recyclerView.adapter?.let {
        val adapter = it as HadethCatlogAdapter
        adapter.submitList(data)
    }
}

@BindingAdapter("dayPrayers")
fun bindPrayersRecyclerView(recyclerView: RecyclerView, data: List<Prayer>?) {
    val adapter = recyclerView.adapter as PrayersListAdapter
    adapter.submitList(data)
}



