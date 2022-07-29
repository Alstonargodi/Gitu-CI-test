package com.example.core.di

import com.example.core.domain.local.FavoriteInteractor
import com.example.core.domain.local.FavoriteUseCase
import com.example.core.domain.remote.RemoteInteractor
import com.example.core.domain.remote.RemoteUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun provideRemoteUseCase(interactor: RemoteInteractor): RemoteUseCase

    @Binds
    abstract fun provideFavoriteUseCase(interactor: FavoriteInteractor): FavoriteUseCase

}