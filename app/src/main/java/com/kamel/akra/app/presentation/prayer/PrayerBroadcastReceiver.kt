package com.kamel.akra.app.presentation.prayer

import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.kamel.akra.R
import com.kamel.akra.data.room.getDatabase
import com.kamel.akra.data.utils.*
import com.kamel.akra.domain.entities.Prayer

class PrayerBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null && intent != null) {
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val notification: Notification? = intent.getParcelableExtra(NOTIFICATION)
            val notificationId: Int = intent.getIntExtra(NOTIFICATION_ID_KEY, 0)
            val prayerId: Int = intent.getIntExtra(PRAYER_ID, 0)

            if (notification != null)
                notificationManager.notify(notificationId, notification)

            val nextUpcomingPrayer:Prayer = getDatabase(context).prayersDao.getUpcomingPrayer()
            val nextUpcomingPrayerLiveData = MutableLiveData<Prayer>()
            nextUpcomingPrayerLiveData.value = nextUpcomingPrayer

            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed({
                var prayerObserver: Observer<Prayer>? = null
                prayerObserver = Observer { upcomingPrayer ->
                    Log.e("TAG", "onReceive: $upcomingPrayer" )
                    if (upcomingPrayer != null && upcomingPrayer.id != prayerId) AzanNotification.scheduleNotification(
                        context,
                        AzanNotificationData(
                            upcomingPrayer.id,
                            upcomingPrayer.id.toPrayerName(context.resources),
                            context.resources.getString(
                                R.string.prayer_time_notification_come,
                                upcomingPrayer.id.toPrayerName(context.resources, showAthan = false)
                            ),
                            upcomingPrayer.dateTime
                        )
                    )
                    prayerObserver?.let { obs -> nextUpcomingPrayerLiveData.removeObserver(obs) }
                }
                nextUpcomingPrayerLiveData.observeForever(prayerObserver)
                Log.e("TAG", "nextUpcomingPrayerLiveData: ${nextUpcomingPrayerLiveData.value}" )
            }, 3000)

        }
    }

}