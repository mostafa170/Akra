package com.kamel.akra.data.models

import com.kamel.akra.app.utilsView.MyDate
import com.kamel.akra.domain.entities.Prayer
import java.util.*

data class PrayersSuccessModel(
    val code: Int,
    val status: String,
    val data: List<PrayersData>
)


data class PrayersData(
    val timings: Timings,
    val date: Date,
)

data class Timings(
    val Fajr: String,
    val Sunrise: String,
    val Dhuhr: String,
    val Asr: String,
    val Maghrib: String,
    val Isha: String
)

data class Date(
    val gregorian: Gregorian,
    val hijri: Hijri
)

data class Gregorian(
    val date: String,
    val format: String
)

data class Hijri(
    val date: String,
    val format: String
)

