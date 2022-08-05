package com.example.core.data.local.entity.remote.userfollower

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class GithubUserFollower(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "avatarUrl")
    val avatarUrl: String,
): Parcelable