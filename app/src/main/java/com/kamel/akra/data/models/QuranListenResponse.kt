package com.kamel.akra.data.models

import com.google.gson.annotations.SerializedName
import com.kamel.akra.domain.entities.QuranAudio

data class QuranListenResponse(

	@field:SerializedName("data")
	val data: List<QuranListenItem>,

	@field:SerializedName("count")
	val count: Int,

	@field:SerializedName("message")
	val message: String
)

data class QuranListenItem(

	@field:SerializedName("pageNumber")
	val pageNumber: Int,

	@field:SerializedName("readerName")
	val readerName: String,

	@field:SerializedName("readerLogo")
	val readerLogo: String,

	@field:SerializedName("link")
	val link: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("sora")
	val sora: String,

	@field:SerializedName("soraNumber")
	val soraNumber: Int,

	@field:SerializedName("ayatsNumber")
	val ayatsNumber: Int
)
fun List<QuranListenItem>.toDomainQuranListen()= map {
	QuranAudio(
		soraName = it.sora,
		soraNumber = it.soraNumber.toString(),
		ayaNumber = it.ayatsNumber.toString(),
		readerName = it.readerName,
		link = it.link,
		logo = it.readerLogo,
		it.type == "مكية"

	)
}
