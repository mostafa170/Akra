package com.kamel.akra.data.utils

object ParseUtils {
    fun parseStringDate(date: String): String = try {
        date.replace("T", "")
    }catch (e: NumberFormatException){
        "error"
    }
}