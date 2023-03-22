package com.kamel.akra.data.models

import com.google.gson.annotations.SerializedName
import com.kamel.akra.domain.entities.Quran

data class QuranListResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val data: List<QuranDataItem>,

	@field:SerializedName("status")
	val status: String
)

data class QuranDataItem(

	@field:SerializedName("number")
	val number: Int,

	@field:SerializedName("englishName")
	val englishName: String,

	@field:SerializedName("numberOfAyahs")
	val numberOfAyahs: Int,

	@field:SerializedName("revelationType")
	val revelationType: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("englishNameTranslation")
	val englishNameTranslation: String
)
fun List<QuranDataItem>.toQuranListDomain() = map {
	Quran(
		it.number,
		it.name,
		it.revelationType == "Meccan"
	)
}
