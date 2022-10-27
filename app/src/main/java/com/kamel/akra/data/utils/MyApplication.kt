package com.kamel.akra.data.utils

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        //getDatabase(this) //--AppDatabase_Impl does not exist
    }

    companion object{
        lateinit var instance: MyApplication
            private set // Only Application can set the instance value
    }
}