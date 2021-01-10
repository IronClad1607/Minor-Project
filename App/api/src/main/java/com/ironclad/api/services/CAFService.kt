package com.ironclad.api.services

import com.ironclad.api.models.entities.User
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface CAFService {

    @GET("/allUser")
    suspend fun getAllUsers(): Response<List<User>>

    @GET("/allUser/{id}")
    suspend fun getUserById(
        @Path("id") id: Int
    ): Response<User>

    @Multipart
    @POST("/fetchResult")
    suspend fun getResult(
        @Part img: MultipartBody.Part
    ): Response<List<User>>
}