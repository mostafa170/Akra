package com.kamel.akra.app.presentation.prayer

import android.os.Parcel
import android.os.Parcelable

data class AzanNotificationData(
    val prayerId: Int,
    val prayerName: String,
    val notificationMessage: String,
    val prayerTime: Long,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readLong()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(prayerId)
        parcel.writeString(prayerName)
        parcel.writeString(notificationMessage)
        parcel.writeLong(prayerTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AzanNotificationData> {
        const val AZAN_NOTIFICATION: String = "azan_notification"

        override fun createFromParcel(parcel: Parcel): AzanNotificationData {
            return AzanNotificationData(parcel)
        }

        override fun newArray(size: Int): Array<AzanNotificationData?> {
            return arrayOfNulls(size)
        }
    }
}
