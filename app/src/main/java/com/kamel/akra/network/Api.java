package com.kamel.akra.network;


import com.kamel.akra.ui.Azkar.AzkarlListan.model.AzkarItemData;
import com.kamel.akra.ui.radio.model.RadiosResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
    @GET("radio/radio_ar.json")
    Call<RadiosResponse> getRadioChannels();

    @POST("Azkar.php")
    Call<List<AzkarItemData>> getAzkarListan();
}
