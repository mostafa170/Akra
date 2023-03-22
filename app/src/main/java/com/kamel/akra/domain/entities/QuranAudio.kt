package com.kamel.akra.domain.entities

data class QuranAudio(
    val soraName:String,
    val soraNumber:String,
    val ayaNumber: String,
    val readerName: String,
    val link: String,
    val logo: String,
    val isMecca:Boolean
)
