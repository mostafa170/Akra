package com.kamel.akra.app.presentation.radio

import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.support.v4.media.session.MediaSessionCompat

class AudioPlayerService : Service(){

    //private lateinit var playerNotificationManager: MeNotificationManager
    private lateinit var notificationManager: NotificationManager
    private lateinit var mediaSession: MediaSessionCompat
    private var mediaPlayer: MediaPlayer = MediaPlayer()



    inner class LocalBinder: Binder(){
        fun getService(): AudioPlayerService = this@AudioPlayerService
    }


    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onCreate() {
        super.onCreate()

    }

    override fun onDestroy() {
        super.onDestroy()
    }


}