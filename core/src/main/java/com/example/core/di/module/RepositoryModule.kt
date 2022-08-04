package com.example.core.di.module

import com.example.core.data.repository.favorite.FavoriteRepository
import com.example.core.data.repository.favorite.IFavoriteRepository
import com.example.core.data.repository.remote.IRemoteRepository
import com.example.core.data.repository.remote.RemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRemoteRepository(repository: RemoteRepository):
            IRemoteRepository
    @Binds
    abstract fun provideFavoriteRepository(repository: FavoriteRepository):
            IFavoriteRepository
}