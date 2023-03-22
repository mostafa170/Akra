package com.kamel.akra.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Quran(
    val id:Int,
    val name: String,
    val isMecca:Boolean
): Parcelable
