package com.kamel.akra.data.models

import com.google.gson.annotations.SerializedName
import com.kamel.akra.domain.entities.AzkarCategory
import com.kamel.akra.domain.entities.Zekr

data class AzkarResponse(

	@field:SerializedName("أذكار الصباح")
	val morning: List<AzkarItem>,

	@field:SerializedName("أذكار الاستيقاظ")
	val wakeUp: List<AzkarItem>,

	@field:SerializedName("أدعية قرآنية")
	val doaaQuran: List<AzkarItem>,

	@field:SerializedName("تسابيح")
	val tasabeh: List<AzkarItem>,

	@field:SerializedName("أدعية الأنبياء")
	val doaaNabwy: List<AzkarItem>,

	@field:SerializedName("أذكار بعد السلام من الصلاة المفروضة")
	val azkarAfterPrayer: List<AzkarItem>,

	@field:SerializedName("أذكار النوم")
	val sleep: List<AzkarItem>,

	@field:SerializedName("أذكار المساء")
	val evening: List<AzkarItem>
)

data class AzkarItem(

	@field:SerializedName("reference")
	val reference: String,

	@field:SerializedName("count")
	val count: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("content")
	val content: String
)


fun AzkarItem.toDomainAzkar() =
	Zekr(
		content,
		count.toInt(),
		description
	)

fun List<AzkarItem>.toEntityDomain() = map {
	it.toDomainAzkar()
}

fun AzkarResponse.toEntityAzkarDomain() =
	AzkarCategory(
		morning.toEntityDomain(),
		wakeUp.toEntityDomain(),
		doaaQuran.toEntityDomain(),
		tasabeh.toEntityDomain(),
		doaaNabwy.toEntityDomain(),
		azkarAfterPrayer.toEntityDomain(),
		sleep.toEntityDomain(),
		evening.toEntityDomain(),
	)
