package com.kamel.akra.data.models

import com.google.gson.annotations.SerializedName
import com.kamel.akra.domain.entities.HadethCategories

data class HadethResponse(

	@field:SerializedName("HadethResponse")
	val hadethResponse: List<HadethResponseItem>
)

data class HadethResponseItem(

	@field:SerializedName("hadeeths_count")
	val hadeethsCount: String,

	@field:SerializedName("parent_id")
	val parentId: Any,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("title")
	val title: String
)

fun List<HadethResponseItem>.toEntity() = map {
	HadethCategories(
		it.id.toInt(),
		it.title,
		it.hadeethsCount.toInt()
	)
}
