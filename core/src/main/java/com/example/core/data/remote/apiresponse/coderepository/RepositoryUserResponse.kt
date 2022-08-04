package com.example.core.data.remote.apiresponse.coderepository

import com.google.gson.annotations.SerializedName

class RepositoryUserResponse : ArrayList<RepositoryUserResponseItem>()

data class RepositoryUserResponseItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("forks_count")
    val forksCount: Int,
    @SerializedName("language")
    val language: String? = "",
    @SerializedName("name")
    val name: String,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
)