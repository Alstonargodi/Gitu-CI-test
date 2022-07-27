package com.example.githubuser.di

import android.content.Context
import com.example.githubuser.core.domain.local.FavoriteInteractor
import com.example.githubuser.core.domain.local.FavoriteUseCase
import com.example.githubuser.core.domain.remote.RemoteInteractor
import com.example.githubuser.core.domain.remote.RemoteUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun provideRemoteUseCase(interactor: RemoteInteractor): RemoteUseCase

    @Binds
    abstract fun provideFavoriteUseCase(interactor: FavoriteInteractor): FavoriteUseCase

}