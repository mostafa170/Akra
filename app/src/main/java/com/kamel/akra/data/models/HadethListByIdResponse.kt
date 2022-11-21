package com.kamel.akra.data.models

import com.google.gson.annotations.SerializedName

data class HadethListByIdResponse(

	@field:SerializedName("data")
	val data: List<HadethListByIdItem>,

	@field:SerializedName("meta")
	val meta: Meta
)

data class Meta(

	@field:SerializedName("per_page")
	val perPage: Int,

	@field:SerializedName("last_page")
	val lastPage: Int,

	@field:SerializedName("total_items")
	val totalItems: Int,

	@field:SerializedName("current_page")
	val currentPage: Int
)

data class HadethListByIdItem(

	@field:SerializedName("translations")
	val translations: List<String>,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("title")
	val title: String
)
