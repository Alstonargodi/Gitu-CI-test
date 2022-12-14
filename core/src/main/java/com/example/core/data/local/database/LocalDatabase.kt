package com.example.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.local.dao.FavoriteDao
import com.example.core.data.local.dao.RemoteDao
import com.example.core.data.local.entity.userlist.GithubListUser
import com.example.core.data.local.entity.favorite.favoriteproject.FavoriteProject
import com.example.core.data.local.entity.favorite.favoriteuser.FavoriteUser
import com.example.core.data.local.entity.remote.userdetail.GithubUserDetail
import com.example.core.data.local.entity.remote.userfollower.GithubUserFollower
import com.example.core.data.local.entity.remote.userfollowing.GithubUserFollowing
import com.example.core.data.local.entity.remote.userproject.GithubUserProject

@Database(
    entities = [
        GithubListUser::class,
        GithubUserDetail::class,
        GithubUserProject::class,
        GithubUserFollower::class,
        GithubUserFollowing::class,
        FavoriteProject::class,
        FavoriteUser::class
    ],
    version = 1,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun favoriteDao() : FavoriteDao
    abstract fun listUserDao() : RemoteDao
}