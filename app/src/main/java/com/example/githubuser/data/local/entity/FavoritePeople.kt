package com.example.githubuser.data.local.entity


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
    @ColumnInfo(name = "location")
    val location: String? =null,
    @ColumnInfo(name = "isSaved")
    val isSaved : Boolean,
): Parcelable