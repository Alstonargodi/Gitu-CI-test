package com.example.githubuser.data.remote.githubresponse.follow


import com.google.gson.annotations.SerializedName

data class FollowResponseItem(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("login")
    val login: String,
)