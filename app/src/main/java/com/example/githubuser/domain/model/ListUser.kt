package com.example.githubuser.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListUser(
    val name : String = "",
    val username : String? = "",
    val imageLink : String? ="",
    val location: String? =null,
    val company: String? =null,
    val isSaved : Boolean,
): Parcelable