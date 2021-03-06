package com.kamel.akra.network;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PrayerTimeAPI {

    private static Retrofit retrofitInstance;

    private  static Retrofit getInstance(){
        if(retrofitInstance==null){
            HttpLoggingInterceptor logging =
                    new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                        @Override public void log(String message) {
                            Log.e("retrofit",message);
                        }
                    });
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();
            retrofitInstance=new Retrofit.Builder()
                    .baseUrl("https://api.pray.zone/v2/times/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofitInstance;
    }


    public static Api getApis(){
        Api apis=getInstance().create(Api.class);
        return apis;
    }
}
