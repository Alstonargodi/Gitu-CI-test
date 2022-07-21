package com.example.githubuser.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubuser.data.local.dao.FavoriteDao
import com.example.githubuser.data.local.dao.ListUserDao
import com.example.githubuser.data.local.entity.userlist.GithubListUser
import com.example.githubuser.data.local.entity.favoriteproject.FavoriteProject

@Database(
    entities = [
        GithubListUser::class,
        FavoriteProject::class
    ],
    version = 4
)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao() : FavoriteDao
    abstract fun listUserDao() : ListUserDao

    companion object{
        @Volatile
        private var INSTANCE : FavoriteDatabase? = null

        @JvmStatic
        fun setDatabase(context : Context): FavoriteDatabase {
            if (INSTANCE == null){
                synchronized(FavoriteDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FavoriteDatabase::class.java,
                        "FavoriteDatabase"
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE as FavoriteDatabase
        }
    }


}