package com.example.core.data.local.entity.favorite.favoriteuser

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class FavoriteUser(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    @ColumnInfo(name = "name")
    val name : String = "",
    @ColumnInfo(name = "username")
    val username : String? = "",
    @ColumnInfo(name = "imageLink")
    val imageLink : String? ="",
    @ColumnInfo(name = "location")
    val location: String? =null,
    @ColumnInfo(name = "company")
    val company: String? =null,
): Parcelable