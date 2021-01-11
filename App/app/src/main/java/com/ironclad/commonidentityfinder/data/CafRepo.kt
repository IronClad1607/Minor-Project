package com.ironclad.commonidentityfinder.data

import com.ironclad.api.CAFClient
import okhttp3.MultipartBody

object CafRepo {
    private val api = CAFClient.cafApi

    suspend fun getAllUsers() = api.getAllUsers().body()

    suspend fun getUserById(id: Int) = api.getUserById(id).body()

    suspend fun getResult(img: MultipartBody.Part) = api.getResult(img).body()
}