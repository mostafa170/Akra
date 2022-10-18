package com.kamel.akra.domain.entities


data class Zekr(
    val content: String,
    val count: Int,
    val description: String,
)

data class AzkarCategory(
    val morning: List<Zekr>,
    val wakeUp: List<Zekr>,
    val doaaQuran: List<Zekr>,
    val tasabeh: List<Zekr>,
    val doaaNabwy: List<Zekr>,
    val azkarAfterPrayer: List<Zekr>,
    val sleep: List<Zekr>,
    val evening: List<Zekr>,

    )
