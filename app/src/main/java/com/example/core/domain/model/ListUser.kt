package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ListUser(
    val name : String = "",
    val username : String? = "",
    val imageLink : String? ="",
    val location: String? =null,
    val company: String? =null,
    val isSaved : Boolean,
): Parcelable