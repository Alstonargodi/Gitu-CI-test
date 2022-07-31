package com.example.core.data.local.entity.githubrepository

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class GithubRepositoryList(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "name")
    val name : String = "",
    @ColumnInfo(name = "description")
    val description : String? = "",
    @ColumnInfo(name = "language")
    val language : String? = "",
    @ColumnInfo(name = "isSaved")
    val isSaved : Boolean
) : Parcelable
