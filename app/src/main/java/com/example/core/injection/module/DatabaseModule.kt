package com.example.core.injection.module

import android.content.Context
import androidx.room.Room
import com.example.core.data.local.dao.FavoriteDao
import com.example.core.data.local.dao.ListUserDao
import com.example.core.data.local.database.LocalDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): LocalDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            LocalDatabase::class.java,
            "LocalDB"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun favoriteDao(database : LocalDatabase):FavoriteDao = database.favoriteDao()

    @Provides
    fun listUserDao(database : LocalDatabase):ListUserDao = database.listUserDao()
}