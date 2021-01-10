package com.ironclad.api.models.entities


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "age")
    val age: Int?,
    @Json(name = "gender")
    val gender: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "marital_status")
    val maritalStatus: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "place_of_birth")
    val placeOfBirth: String?,
    @Json(name = "image_url")
    val imageUrl: String?
)