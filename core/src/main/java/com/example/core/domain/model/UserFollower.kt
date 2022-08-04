package com.example.core.domain.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class UserFollower(
    val id: Int,
    val username: String,
    val avatarUrl: String,
)