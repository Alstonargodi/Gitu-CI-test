package com.example.core.di.module

import android.content.Context
import androidx.room.Room
import com.example.core.data.local.dao.FavoriteDao
import com.example.core.data.local.dao.RemoteDao
import com.example.core.data.local.database.LocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    private val passPhrase : ByteArray = SQLiteDatabase.getBytes("gitu".toCharArray())
    val factory = SupportFactory(passPhrase)
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext
        context: Context
    ): LocalDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            LocalDatabase::class.java,
            "LocalDB"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()

    @Provides
    fun favoriteDao(database : LocalDatabase):FavoriteDao = database.favoriteDao()

    @Provides
    fun listUserDao(database : LocalDatabase):RemoteDao = database.listUserDao()
}