package com.kamel.akra.app.utilsView

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object  MyDate
{
    fun getDate(date: String?, format: String?, fromLocale: Locale?): Date? {
        val dateFormat = SimpleDateFormat(format, fromLocale)
        return try {
            dateFormat.parse(date)
        } catch (e: Exception) {
            null
        }
    }


    /**
     * <h3>millisecondsToCountDownString</h3>
     *
     * converts milliseconds to count down String format HH:MM:SS
     *
     * @param milliseconds long milliseconds to be converted.
     * @return String
     */
     fun millisecondsToCountDownString(milliseconds: Long): String? {
        val totalSecs = milliseconds / 1000
        val hours = totalSecs / 3600
        val mins = totalSecs / 60 % 60
        val secs = totalSecs % 60
        val minsString = if (mins == 0L) "00" else if (mins < 10) "0$mins" else "" + mins
        val secsString = if (secs == 0L) "00" else if (secs < 10) "0$secs" else "" + secs
        return secsString
    }

    fun convertDateToDateString(date: Date?, format: String?, toLocale: Locale?): String {
        val dateFormat = SimpleDateFormat(format, toLocale)
        return try {
            dateFormat.format(date)
        } catch (e: Exception) {
            return e.toString()
        }
    }

    fun convertStringDateToString(
        fromFormat: String?,
        toFormat: String?,
        date: String?,
        fromLocale: Locale?,
        toLocale: Locale?
    ): String? {
        var dateFormat = SimpleDateFormat(fromFormat, fromLocale)
        return try {
            val parse: Date = dateFormat.parse(date)
            dateFormat = SimpleDateFormat(toFormat, toLocale)
            if (parse != null) dateFormat.format(parse) else date
        } catch (e: Exception) {
            date
        }
    }

    fun convertStringDateToString(
        fromFormat: String?,
        toFormat: String?,
        date: String?,
        fromLocale: Locale?,
        toLocale: Locale?,
        timeZone: TimeZone?
    ): String? {
        var dateFormat = SimpleDateFormat(fromFormat, fromLocale)
        if (timeZone != null) dateFormat.setTimeZone(timeZone)
        return try {
            val parse: Date = dateFormat.parse(date)
            dateFormat = SimpleDateFormat(toFormat, toLocale)
            if (parse != null) dateFormat.format(parse) else date
        } catch (e: Exception) {
            date
        }
    }

    @SuppressLint("NewApi")
    fun formatDate(dateAPi: String?): String {
        val outputFormat: DateFormat = SimpleDateFormat("dd/MM/yy")
        val inputFormat: DateFormat =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
        var date: Date? = null
        try {
            date = inputFormat.parse(dateAPi)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return outputFormat.format(date)
    }

    @SuppressLint("NewApi")
    fun formatDateTime(dateAPi: String?): String {
        val outputFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm")
        val inputFormat: DateFormat =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
        var date: Date? = null
        try {
            date = inputFormat.parse(dateAPi)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return outputFormat.format(date)
    }




}