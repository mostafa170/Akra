package com.kamel.akra.data.utils

import android.content.SharedPreferences
import androidx.preference.PreferenceManager

object SharedPreferencesUtils {
    private const val preferencesFile: String = "com.aldamina.aldaminadelivery.PREFERENCE_FILE"

    private val sharedPref: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(MyApplication.instance)
    }

    private val sharedPrefEditor: SharedPreferences.Editor by lazy {
        sharedPref.edit()
    }

    fun saveString(key: String, value: String) = sharedPrefEditor.putString(key, value).apply()
    fun getString(key: String, defaultValue: String?) = sharedPref.getString(key, defaultValue) ?: ""

    fun saveInt(key: String, value: Int) = sharedPrefEditor.putInt(key, value).apply()
    fun getInt(key: String, defaultValue: Int = 0) = sharedPref.getInt(key, defaultValue)

    fun saveLong(key: String, value: Long) = sharedPrefEditor.putLong(key, value).apply()
    fun getLong(key: String, defaultValue: Long = 0) = sharedPref.getLong(key, defaultValue)

    fun saveFloat(key: String, value: Float) = sharedPrefEditor.putFloat(key, value).apply()
    fun getFloat(key: String, defaultValue: Float = 0f) = sharedPref.getFloat(key, defaultValue)

    fun saveBoolean(key: String, value: Boolean) = sharedPrefEditor.putBoolean(key, value).apply()
    fun getBoolean(key: String, defaultValue: Boolean) = sharedPref.getBoolean(key, defaultValue)

    fun clear() = sharedPrefEditor.clear().apply()
}