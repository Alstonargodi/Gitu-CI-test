package com.example.core.data.local.entity.remote.userdetail

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class GithubUserDetail(
    @PrimaryKey(autoGenerate = false)
    val id : Int,
    @ColumnInfo(name = "userName")
    val userName: String,
    @ColumnInfo(name = "nickName")
    val nickName: String,
    @ColumnInfo(name = "bio")
    val bio: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "blog")
    val blog: String,
    @ColumnInfo(name = "company")
    val company: String,
    @ColumnInfo(name = "public_repos")
    val publicRepos: Int,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "followers")
    val followers: Int,
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String,
    @ColumnInfo(name = "following")
    val following: Int,
    @ColumnInfo(name = "location")
    val location: String,
): Parcelable