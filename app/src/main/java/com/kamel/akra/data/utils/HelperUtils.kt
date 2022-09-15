package com.kamel.akra.data.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import java.util.*


object HelperUtils {

    fun openGoogleMap(context: Context , latitude:String, longitude:String){
        val uri: String =
                    java.lang.String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        intent.setPackage("com.google.android.apps.maps")
        context.startActivity(intent)
    }


    fun callPhone(context: Context, phoneNumber:String) {
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:$phoneNumber")
        context.startActivity(dialIntent)
    }

    fun getDirections(context: Context,latFrom:Double,lngFrom:Double,latTo:Double,lngTo:Double){
        val uri =
            "http://maps.google.com/maps?saddr=" + latFrom +
                    "," + lngFrom + "&daddr=" + latTo + "," + lngTo
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        context.startActivity(intent)
    }
}