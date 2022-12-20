package com.kamel.akra.data.models

import com.google.gson.annotations.SerializedName
import com.kamel.akra.domain.entities.HadethDetails
import com.kamel.akra.domain.entities.WordsMeanings

data class HadethOneDetailsResponse(

	@field:SerializedName("reference")
	val reference: String,

	@field:SerializedName("hadeeth")
	val hadeeth: String,

	@field:SerializedName("words_meanings")
	val wordsMeanings: List<WordsMeaningsItem>,

	@field:SerializedName("hints")
	val hints: List<String>,

	@field:SerializedName("translations")
	val translations: List<String>,

	@field:SerializedName("grade")
	val grade: String,

	@field:SerializedName("attribution")
	val attribution: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("categories")
	val categories: List<String>,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("explanation")
	val explanation: String
)

data class WordsMeaningsItem(

	@field:SerializedName("meaning")
	val meaning: String,

	@field:SerializedName("word")
	val word: String
)

fun HadethOneDetailsResponse.toHadethDetailsEntity() =
	HadethDetails(
		id = id.toInt(),
		contentHadeeth = hadeeth,
		title = title,
		attributionGrade = attribution + grade,
		explanation = explanation,
		wordsMeanings = wordsMeanings.toWordsMeaningsEntity()

	)

fun List<WordsMeaningsItem>.toWordsMeaningsEntity() = map {
	WordsMeanings(
		it.word,
		it.meaning
	)
}
