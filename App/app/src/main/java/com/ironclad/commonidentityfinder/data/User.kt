package com.ironclad.commonidentityfinder.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val age: Int?,
    val gender: String?,
    val id: Int?,
    val maritalStatus: String?,
    val name: String?,
    val placeOfBirth: String?,
    val imageUrl: String?
) : Parcelable