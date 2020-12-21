package com.ironclad.api.services

import com.ironclad.api.models.entities.User
import com.ironclad.api.models.response.AllUserResponse
import retrofit2.Response
import retrofit2.http.GET

interface CAFService {

    @GET("/allUser")
    suspend fun getAllUsers(): Response<List<User>>
}