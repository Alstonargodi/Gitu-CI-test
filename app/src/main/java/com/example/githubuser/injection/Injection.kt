package com.example.githubuser.injection

import android.content.Context
import com.example.githubuser.data.local.database.FavoriteDatabase
import com.example.githubuser.data.remote.apiconfig.ApiConfig
import com.example.githubuser.data.remote.apiservice.ApiService
import com.example.githubuser.data.repository.FavoriteRepository
import com.example.githubuser.data.repository.RemoteRepository

object Injection {
    fun provideRemoteRepository(): RemoteRepository{
        return RemoteRepository(ApiConfig.getApiService())
    }

    fun provideLocalRepository(context: Context): FavoriteRepository{
        return FavoriteRepository(FavoriteDatabase.setDatabase(context))
    }
}