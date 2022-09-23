package com.kamel.akra.data.network

import com.kamel.akra.data.models.PrayersSuccessModel
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.*


interface APICalls {

//    @GET("radio/radio_ar.json")
//    fun getRadioChannels(): Call<RadiosResponse?>?
//
//    @POST("Azkar.php")
//    fun getAzkarListan(): Call<List<AzkarItemData?>?>?
//
//    @GET("day.json?")
//    fun getPrayerTime(
//        @Query("longitude") longitude: String?,
//        @Query("latitude") latitude: String?,
//        @Query("elevation") elevation: String?,
//        @Query("date") date: String?,
//        @Query("timeformat") timeformat: String?
//    ): Call<PrayerTimeResponse?>?
//
//    @FormUrlEncoded
//    @POST("QuranShared.php")
//    fun getElmoshafListan(
//        @Header("lang") lang: String?,
//        @Field("reader_id") reader_id: Int
//    ): Call<ResponseQuran?>?

    @GET("v1/calendar?")
    fun downloadMonthPrayersAsync(@QueryMap queries: Map<String, String>): Deferred<PrayersSuccessModel>
}