package com.kamel.akra.data.network

import com.kamel.akra.data.models.*
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*


interface APICalls {

    @GET("radio/radio_ar.json")
    fun getRadioChannelsAsync(): Deferred<Response<RadioChannelResponse>>
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

    @GET("categories/list/?language=ar")
    fun downloadHadethsCategoriesAsync(): Deferred<List<HadethResponseItem>>

    @GET("hadeeths/list/?language=ar")
    fun downloadHadethsListByIdAsync(@Query("category_id") category_id: Int,@Query("page") page: Int): Deferred<HadethListByIdResponse>

    @GET("hadeeths/one/?language=ar")
    fun getHadethOneDetailsAsync(@Query("id") hadeth_id: Int): Deferred<HadethOneDetailsResponse>


}