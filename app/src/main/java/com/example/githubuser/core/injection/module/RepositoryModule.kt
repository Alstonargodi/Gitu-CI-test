package com.example.githubuser.core.injection.module

import com.example.githubuser.core.data.repository.favorite.FavoriteRepository
import com.example.githubuser.core.data.repository.favorite.IFavoriteRepository
import com.example.githubuser.core.data.repository.remote.IRemoteRepository
import com.example.githubuser.core.data.repository.remote.RemoteRepository
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