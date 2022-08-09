package com.example.core.data.local.entity.remote.userproject

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class GithubUserProject(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String? = "",
    @ColumnInfo(name = "forks_count")
    val forksCount: Int,
    @ColumnInfo(name = "language")
    val language: String? = "",
    @ColumnInfo(name ="stargazers_count")
    val stargazersCount: Int,
): Parcelable