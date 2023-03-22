package com.kamel.akra.data.network

import android.util.Log
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kamel.akra.data.utils.SharedPreferencesData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object QuranApiManager {
    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val loggingInterceptor = HttpLoggingInterceptor { message ->
        Log.e("retrofit", message)
    }
    private val client = OkHttpClient().newBuilder()
        .addInterceptor(loggingInterceptor.apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor{ chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("charset", "UTF-8")
                //.addHeader("Authorization", "Bearer ${SharedPreferencesData.getAuthToken()}")
                .build()
            chain.proceed(newRequest)
        }
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(client)
        .baseUrl("https://gad25.xyz/Quran/")
        .build()

    val apiCalls : APICalls by lazy {
        retrofit.create(APICalls::class.java)
    }
}