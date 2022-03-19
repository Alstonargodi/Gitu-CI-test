package com.example.githubuser.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubuser.local.entity.FavoritePeople
import com.example.githubuser.local.entity.FavoriteProject

@Database(
    entities = [
        FavoritePeople::class,
        FavoriteProject::class
    ],
    version = 1
)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoritedao() : FavoriteDao

    companion object{
        @Volatile
        private var INSTANCE : FavoriteDatabase? = null

        @JvmStatic
        fun setDatabase(context : Context): FavoriteDatabase{
            if (INSTANCE == null){
                synchronized(FavoriteDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FavoriteDatabase::class.java,
                        "FavoriteDatabase"
                    ).build()
                }
            }
            return INSTANCE as FavoriteDatabase
        }
    }


}