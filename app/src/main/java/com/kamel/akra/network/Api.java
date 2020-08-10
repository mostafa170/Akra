package com.kamel.akra.network;


import com.kamel.akra.ui.radio.model.RadiosResponse;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
    @GET("radio/radio_ar.json")
    Call<RadiosResponse> getRadioChannels();
}
