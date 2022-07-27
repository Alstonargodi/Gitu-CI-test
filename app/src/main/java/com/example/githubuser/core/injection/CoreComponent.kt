package com.example.githubuser.core.injection

import android.content.Context
import com.example.githubuser.core.data.repository.favorite.IFavoriteRepository
import com.example.githubuser.core.data.repository.remote.IRemoteRepository
import com.example.githubuser.core.injection.module.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RepositoryModule::class]
)
interface CoreComponent {

    @Component.Factory
    interface Factory{
        fun create(
            @BindsInstance
            context: Context
        ): CoreComponent
    }

    fun provideRemoteRepository(): IRemoteRepository

    fun provideFavoriteRepository(): IFavoriteRepository
}