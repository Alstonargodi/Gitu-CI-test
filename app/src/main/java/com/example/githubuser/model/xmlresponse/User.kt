package com.example.githubuser.model.xmlresponse

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class User(
   val user : String,
   val username : String,
   val location : String,
   val repo : String,
   val company : String,
   val follower : String,
   val following : String,
   val avatar : Int
): Parcelable