package com.example.githubuser.data.remote.githubresponse.repository


import com.google.gson.annotations.SerializedName

data class RepoResponseItem(
    @SerializedName("description")
    val description: String,
    @SerializedName("forks_count")
    val forksCount: Int,
    @SerializedName("language")
    val language: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
)