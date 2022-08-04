package com.example.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.local.dao.FavoriteDao
import com.example.core.data.local.dao.ListUserDao
import com.example.core.data.local.entity.userlist.GithubListUser
import com.example.core.data.local.entity.favoriteproject.FavoriteProject
import com.example.core.data.local.entity.favoriteuser.FavoriteUser

@Database(
    entities = [
        GithubListUser::class,
        FavoriteProject::class,
        FavoriteUser::class
    ],
    version = 1,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun favoriteDao() : FavoriteDao
    abstract fun listUserDao() : ListUserDao
}