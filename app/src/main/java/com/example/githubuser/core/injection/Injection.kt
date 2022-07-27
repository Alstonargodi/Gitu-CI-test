package com.example.githubuser.core.injection

import android.content.Context
import com.example.githubuser.core.data.local.database.LocalDatabase
import com.example.githubuser.core.data.local.source.FavoriteDataSource
import com.example.githubuser.core.data.local.source.IFavoriteDataSource
import com.example.githubuser.core.data.local.source.LocalDataSource
import com.example.githubuser.core.data.remote.apiconfig.ApiConfig
import com.example.githubuser.core.data.remote.source.IRemoteDataSource
import com.example.githubuser.core.data.remote.source.RemoteDataSource
import com.example.githubuser.core.data.repository.favorite.IFavoriteRepository
import com.example.githubuser.core.data.repository.favorite.FavoriteRepository
import com.example.githubuser.core.data.repository.remote.IRemoteRepository
import com.example.githubuser.core.data.repository.remote.RemoteRepository
import com.example.githubuser.core.domain.local.FavoriteInteractor
import com.example.githubuser.core.domain.local.FavoriteUseCase
import com.example.githubuser.core.domain.remote.RemoteInteractor
import com.example.githubuser.core.domain.remote.RemoteUseCase

object Injection {
//
//    fun provideLocalUseCase(context: Context): FavoriteUseCase {
//        return FavoriteInteractor(provideRepository(context))
//    }
//
//    private fun provideRepository(context: Context): IFavoriteRepository{
//        return FavoriteRepository(provideDataSource(context))
//    }
//
//    private fun provideDataSource(context: Context): IFavoriteDataSource{
//        return FavoriteDataSource(LocalDatabase.setDatabase(context).favoriteDao())
//    }
//
//    fun provideRemoteUseCase(context: Context): RemoteUseCase{
//        return RemoteInteractor(
//            provideRemoteRepository(context)
//        )
//    }
//
//    private fun provideRemoteRepository(context: Context): IRemoteRepository{
//        val database = LocalDatabase.setDatabase(context)
//        return RemoteRepository(
//            provideRemoteSource(),
//            LocalDataSource.getInstance(database.listUserDao())
//        )
//    }
//
//    private fun provideRemoteSource(): IRemoteDataSource{
//        return RemoteDataSource(ApiConfig.getApiService())
//    }


}