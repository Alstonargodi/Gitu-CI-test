package com.example.githubuser.local.entity


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class FavoritePeople(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "name")
    val name : String = "",
    @ColumnInfo(name = "username")
    val username : String? = "",
    @ColumnInfo(name = "imageLink")
    val imageLink : String? ="",
    @ColumnInfo(name = "repository")
    val repository : Int? =0,
    @ColumnInfo(name = "follower")
    val follower :  Int? =0,
    @ColumnInfo(name = "following")
    val following : Int? =0,
    @ColumnInfo(name = "bio")
    val bio : String? ="",
    @ColumnInfo(name = "company")
    val company : String? ="",
    @ColumnInfo(name = "location")
    val location: String? =null,
    @ColumnInfo(name = "profileLink")
    val link : String? =null,
    @ColumnInfo(name = "isSaved")
    val isSaved : Boolean,
): Parcelable