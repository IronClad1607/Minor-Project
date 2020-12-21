package com.ironclad.commonidentityfinder.data

import com.ironclad.api.CAFClient

object CafRepo {
    private val api = CAFClient.cafApi

    suspend fun getAllUsers() = api.getAllUsers().body()
}