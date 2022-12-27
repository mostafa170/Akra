package com.kamel.akra.data.utils

import com.google.firebase.messaging.FirebaseMessaging


object SharedPreferencesData {
    fun getLanguage() = SharedPreferencesUtils.getString("_language", null)
    fun setLanguage(value: String) = SharedPreferencesUtils.saveString("_language", value)

    fun isFirstOpen() = SharedPreferencesUtils.getBoolean("_first_open", true)
    fun setIsFirstOpen(value: Boolean) = SharedPreferencesUtils.saveBoolean("_first_open", value)

    fun isVerifyCode() = SharedPreferencesUtils.getBoolean("_verify_code", false)
    fun setIsVerifyCode(value: Boolean) = SharedPreferencesUtils.saveBoolean("_verify_code", value)

    fun getFCMToken() = SharedPreferencesUtils.getString("_firebasetoken", null)
    fun saveFCMToken(value: String) = SharedPreferencesUtils.saveString("_firebasetoken", value)

    fun setAuthToken(value: String) = SharedPreferencesUtils.saveString("_auth_token", value)
    fun getAuthToken() = SharedPreferencesUtils.getString("_auth_token", null)

    fun isNotifyPrayer(prayerName: String): Boolean {
        return if (prayerName == SUNRISE) false else SharedPreferencesUtils.getBoolean(
            prayerName,
            true
        )
    }

    fun setNotifyPrayer(prayerName: String, value: Boolean) {
        SharedPreferencesUtils.saveBoolean(prayerName, value)
    }


    fun logout(){
        FirebaseMessaging.getInstance().deleteToken()
        //FirebaseInstallations.getInstance().delete()
        SharedPreferencesUtils.clear()
        setIsFirstOpen(false)
    }
}