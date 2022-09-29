package com.kamel.akra.data.models

import com.google.gson.annotations.SerializedName
import com.kamel.akra.domain.entities.RadioStation

data class RadioChannelResponse(

	@field:SerializedName("Radios")
	val radios: List<RadiosItem>
)

data class RadiosItem(

	@field:SerializedName("URL")
	val uRL: String,

	@field:SerializedName("Name")
	val name: String
)

fun List<RadiosItem>.toEntity() = map {
	RadioStation(
		it.uRL,
		it.name,
	)
}
