package com.ironclad.api

import com.ironclad.api.services.CAFService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object CAFClient {

    private val client = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.MINUTES)
        .writeTimeout(10, TimeUnit.MINUTES)
        .readTimeout(10, TimeUnit.MINUTES)
        .build()


    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl("http://ffcd46a41b96.ngrok.io/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client)
        .build()

    val cafApi = retrofitBuilder.create(CAFService::class.java)
}