package com.kamel.akra.app.presentation.prayer

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.kamel.akra.R
import com.kamel.akra.data.utils.*


object AzanNotification {

    private fun createAzanNotificationsChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val notificationChannel = NotificationChannel(
                PRAYER_NOTIFICATION_CHANNEL_ID,
                PRAYER_NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ALARM)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
            notificationChannel.setSound(
                Uri.parse(
                    ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + context.packageName + "/"+ R.raw.azan
                ), audioAttributes
            )

            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    fun scheduleNotification(context: Context, azanNotificationData: AzanNotificationData) {
        Log.i("TAG", "scheduleNotification: $azanNotificationData")
        createAzanNotificationsChannel(context)

        val channelId = PRAYER_NOTIFICATION_CHANNEL_ID

        val notificationBuilder =
            NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_logo_app)
                .setContentTitle(azanNotificationData.prayerName)
                .setContentText(azanNotificationData.notificationMessage)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setCategory(NotificationCompat.CATEGORY_CALL)
                .setChannelId(channelId)
                .setShowWhen(true)
                .setWhen(azanNotificationData.prayerTime)
                .setAutoCancel(true)
                .setSound(
                    Uri.parse(
                        ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + context.packageName + "/"+ R.raw.azan
                        )
                    )


        val prayerAlarmNotification = notificationBuilder.build()

        val notificationIntent = Intent(context, PrayerBroadcastReceiver::class.java)
        notificationIntent.putExtra(NOTIFICATION_ID_KEY, PRAYER_NOTIFICATION_ID)
        notificationIntent.putExtra(NOTIFICATION, prayerAlarmNotification)
        notificationIntent.putExtra(PRAYER_ID, azanNotificationData.prayerId)

        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getBroadcast(context, PRAYER_NOTIFICATION_REQUEST_CODE, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        } else {
            PendingIntent.getBroadcast(context, PRAYER_NOTIFICATION_REQUEST_CODE, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, azanNotificationData.prayerTime ,pendingIntent)
        else
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, azanNotificationData.prayerTime, pendingIntent)

    }
}