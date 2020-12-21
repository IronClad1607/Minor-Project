package com.ironclad.api

import com.ironclad.api.services.CAFService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object CAFClient {

    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl("https://29a96194f6e9.ngrok.io/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val cafApi = retrofitBuilder.create(CAFService::class.java)
}