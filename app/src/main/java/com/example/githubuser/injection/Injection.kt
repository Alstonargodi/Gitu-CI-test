package com.example.githubuser.injection

import android.content.Context
import com.example.githubuser.data.local.source.FavoriteDataSource
import com.example.githubuser.data.local.source.IFavoriteDataSource
import com.example.githubuser.data.remote.source.IRemoteDataSource
import com.example.githubuser.data.remote.source.RemoteDataSource
import com.example.githubuser.data.repository.favorite.IFavoriteRepository
import com.example.githubuser.data.repository.favorite.FavoriteRepository
import com.example.githubuser.data.repository.remote.IRemoteRepository
import com.example.githubuser.data.repository.remote.RemoteRepository
import com.example.githubuser.domain.local.FavoriteInteractor
import com.example.githubuser.domain.local.FavoriteUseCase
import com.example.githubuser.domain.remote.RemoteInteractor
import com.example.githubuser.domain.remote.RemoteUseCase

object Injection {

    fun provideLocalUseCase(context: Context): FavoriteUseCase {
        return FavoriteInteractor(provideRepository(context))
    }

    private fun provideRepository(context: Context): IFavoriteRepository{
        return FavoriteRepository(provideDataSource(context))
    }

    private fun provideDataSource(context: Context): IFavoriteDataSource{
        return FavoriteDataSource(context)
    }

    fun provideRemoteUseCase(): RemoteUseCase{
        return RemoteInteractor(provideRemoteRepository())
    }

    private fun provideRemoteRepository(): IRemoteRepository{
        return RemoteRepository(provideRemoteSource())
    }

    private fun provideRemoteSource(): IRemoteDataSource{
        return RemoteDataSource()
    }
}