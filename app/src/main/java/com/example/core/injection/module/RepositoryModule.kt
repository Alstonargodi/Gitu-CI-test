package com.example.core.injection.module

import com.example.core.data.repository.favorite.FavoriteRepository
import com.example.core.data.repository.favorite.IFavoriteRepository
import com.example.core.data.repository.remote.IRemoteRepository
import com.example.core.data.repository.remote.RemoteRepository
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        NetworkModule::class,
        DatabaseModule::class
    ]
)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRemoteRepository(repository: RemoteRepository):
            IRemoteRepository

    @Binds
    abstract fun provideFavoriteRepository(repository: FavoriteRepository):
            IFavoriteRepository


}