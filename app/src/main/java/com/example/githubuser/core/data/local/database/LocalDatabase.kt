package com.example.githubuser.core.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubuser.core.data.local.dao.FavoriteDao
import com.example.githubuser.core.data.local.dao.ListUserDao
import com.example.githubuser.core.data.local.entity.userlist.GithubListUser
import com.example.githubuser.core.data.local.entity.favoriteproject.FavoriteProject

@Database(
    entities = [
        GithubListUser::class,
        FavoriteProject::class
    ],
    version = 1,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun favoriteDao() : FavoriteDao
    abstract fun listUserDao() : ListUserDao
}