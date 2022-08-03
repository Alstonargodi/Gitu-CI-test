package com.example.githubuser.presentation.utils.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.domain.local.FavoriteUseCase
import com.example.core.domain.remote.RemoteUseCase
import com.example.core.di.AppScope
import com.example.githubuser.presentation.fragment.detail.DetailViewModel
import com.example.githubuser.presentation.fragment.follower.FollowerViewModel
import com.example.githubuser.presentation.fragment.following.FollowingViewModel
import com.example.githubuser.presentation.fragment.repositoryuser.GithubRepositoryViewModel
import com.example.githubuser.presentation.fragment.home.HomeViewModel
import javax.inject.Inject

@AppScope
class ViewModelFactory @Inject constructor(
    private val favoriteUseCase : FavoriteUseCase,
    private val remoteUseCase: RemoteUseCase,
):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(remoteUseCase) as T
        }else if(modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(remoteUseCase,favoriteUseCase) as T
        }else if(modelClass.isAssignableFrom(FollowerViewModel::class.java)) {
            return FollowerViewModel(remoteUseCase) as T
        }else if(modelClass.isAssignableFrom(FollowingViewModel::class.java)) {
            return FollowingViewModel(remoteUseCase) as T
        }else if(modelClass.isAssignableFrom(GithubRepositoryViewModel::class.java)) {
            return GithubRepositoryViewModel(remoteUseCase,favoriteUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}