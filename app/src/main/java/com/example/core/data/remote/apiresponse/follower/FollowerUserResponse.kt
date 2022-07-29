package com.example.core.data.remote.apiresponse.follower

import com.google.gson.annotations.SerializedName

class FollowerUserResponse : ArrayList<FollowerUserResponseItem>()

data class FollowerUserResponseItem(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("login")
    val login: String,
)