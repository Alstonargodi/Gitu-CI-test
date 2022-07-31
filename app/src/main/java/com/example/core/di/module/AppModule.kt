package com.example.core.di.module

import com.example.core.domain.local.FavoriteInteractor
import com.example.core.domain.local.FavoriteUseCase
import com.example.core.domain.remote.RemoteInteractor
import com.example.core.domain.remote.RemoteUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideRemoteUseCase(interactor: RemoteInteractor): RemoteUseCase

    @Binds
    @Singleton
    abstract fun provideFavoriteUseCase(interactor: FavoriteInteractor): FavoriteUseCase

}