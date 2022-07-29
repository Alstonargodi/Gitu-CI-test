package com.example.core.di

import com.example.core.injection.CoreComponent
import com.example.githubuser.presentation.fragment.book.BookFragment
import com.example.githubuser.presentation.fragment.detail.DetailFragment
import com.example.githubuser.presentation.fragment.favorite.FavoriteFragment
import com.example.githubuser.presentation.fragment.follower.FollowerFragment
import com.example.githubuser.presentation.fragment.following.FollowingFragment
import com.example.githubuser.presentation.fragment.githubrepository.RepositoryFragment
import com.example.githubuser.presentation.fragment.home.HomeFragment
import com.example.githubuser.presentation.fragment.setting.SettingFragment
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {

    @Component.Factory
    interface Factory{
        fun create(coreComponent : CoreComponent): AppComponent
    }

    fun inject(fragment : HomeFragment)
    fun inject(fragment : BookFragment)
    fun inject(fragment : DetailFragment)
    fun inject(fragment : FavoriteFragment)
    fun inject(fragment : FollowerFragment)
    fun inject(fragment : FollowingFragment)
    fun inject(fragment : RepositoryFragment)
    fun inject(fragment : SettingFragment)
}