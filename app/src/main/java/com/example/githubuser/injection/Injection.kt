package com.example.githubuser.injection

import android.content.Context
import com.example.githubuser.data.local.database.FavoriteDatabase
import com.example.githubuser.data.local.source.FavoriteDataSource
import com.example.githubuser.data.local.source.IFavoriteDataSource
import com.example.githubuser.data.remote.apiconfig.ApiConfig
import com.example.githubuser.data.remote.apiservice.ApiService
import com.example.githubuser.data.repository.FavoriteRepository
import com.example.githubuser.data.repository.RemoteRepository
import com.example.githubuser.data.repository.favorite.IFavoriteRepository
import com.example.githubuser.data.repository.favorite.mFavoriteRepository
import com.example.githubuser.domain.FavoriteInteractor
import com.example.githubuser.domain.FavoriteUseCase

object Injection {
    fun provideRemoteRepository(): RemoteRepository{
        return RemoteRepository(ApiConfig.getApiService())
    }

    fun provideLocalRepository(context: Context): FavoriteRepository{
        return FavoriteRepository(FavoriteDatabase.setDatabase(context))
    }

    fun provideUseCase(context: Context): FavoriteUseCase{
        return FavoriteInteractor(provideRepository(context))
    }

    private fun provideRepository(context: Context): IFavoriteRepository{
        return mFavoriteRepository(provideDataSource(context))
    }

    private fun provideDataSource(context: Context): IFavoriteDataSource{
        return FavoriteDataSource(context)
    }
}