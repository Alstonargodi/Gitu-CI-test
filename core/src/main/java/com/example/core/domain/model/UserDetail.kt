package com.example.core.domain.model

data class UserDetail(
    val id : Int,
    val userName: String? = null,
    val nickName: String? = null,
    val bio: String? = null,
    val type: String? = null,
    val blog: String? = null,
    val company: String? = null,
    val publicRepos: Int? = null,
    val email: String? = null,
    val followers: Int? = null,
    val avatarUrl: String? = null,
    val following: Int? = null,
    val location: String? = null,
)
