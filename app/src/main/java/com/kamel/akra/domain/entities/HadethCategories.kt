package com.kamel.akra.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class HadethCategories(
    val id : Int,
    val title: String,
    val count: Int
) : Parcelable

data class HadethListById(
    val hadethList:List<HadethCategories> ,
    val lastPage: Int,
    val currentPage: Int
)

data class HadethDetails(
    val id : Int,
    val contentHadeeth: String,
    val title: String,
    val attributionGrade: String,
    val explanation: String,
    val wordsMeanings: List<WordsMeanings>
)

data class WordsMeanings(
    val word: String,
    val meaning: String,
)


