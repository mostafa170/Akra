package com.kamel.akra.network;


import com.kamel.akra.ui.Azkar.AzkarlListan.model.AzkarItemData;
import com.kamel.akra.ui.ListanElmoshaf.model.ResponseQuran;
import com.kamel.akra.ui.prayerTime.prayerModel.PrayerTimeResponse;
import com.kamel.akra.ui.radio.model.RadiosResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
    @GET("radio/radio_ar.json")
    Call<RadiosResponse> getRadioChannels();

    @POST("Azkar.php")
    Call<List<AzkarItemData>> getAzkarListan();

    @GET("day.json?")
    Call<PrayerTimeResponse> getPrayerTime(@Query("longitude") String longitude,
                                           @Query("latitude") String latitude,
                                           @Query("elevation") String elevation,
                                           @Query("date") String date,
                                           @Query("timeformat") String timeformat);
    @FormUrlEncoded
    @POST("QuranShared.php")
    Call<ResponseQuran> getElmoshafListan(@Header("lang") String lang,
                                          @Field("reader_id") int reader_id) ;
}
