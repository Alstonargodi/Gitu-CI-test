package com.example.core.domain.model

import com.google.gson.annotations.SerializedName

data class UserRepository(
    val name: String,
    val description: String,
    val forksCount: Int,
    val language: String,
    val stargazersCount: Int,
)