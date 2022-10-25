package com.kamel.akra.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Zekr")
data class Zekr(

    @PrimaryKey()
    @ColumnInfo
    val content: String,
    @ColumnInfo
    val count: Int,
    @ColumnInfo
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
