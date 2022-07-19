package com.example.githubuser.presentation.utils.viewmodelfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubuser.domain.local.FavoriteUseCase
import com.example.githubuser.domain.remote.RemoteUseCase
import com.example.githubuser.injection.Injection
import com.example.githubuser.presentation.fragment.detail.DetailViewModel
import com.example.githubuser.presentation.fragment.favorite.FavoriteViewModel
import com.example.githubuser.presentation.fragment.follower.FollowerViewModel
import com.example.githubuser.presentation.fragment.following.FollowingViewModel
import com.example.githubuser.presentation.fragment.githubrepository.GithubRepositoryViewModel
import com.example.githubuser.presentation.fragment.home.HomeViewModel


@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(
    private val favoriteUseCase : FavoriteUseCase,
    private val remoteUseCase: RemoteUseCase
):ViewModelProvider.NewInstanceFactory() {
        companion object{
            @Volatile
            private var instance: ViewModelFactory? = null
            fun getInstance(context: Context): ViewModelFactory {
                if (instance == null){
                    synchronized(ViewModelFactory::class.java){
                        instance = ViewModelFactory(
                            Injection.provideLocalUseCase(context),
                            Injection.provideRemoteRepository()
                        )
                    }
                }
                return instance as ViewModelFactory
            }
        }

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)){
                return FavoriteViewModel(favoriteUseCase) as T
            }else if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
                return HomeViewModel(remoteUseCase) as T
            }else if(modelClass.isAssignableFrom(DetailViewModel::class.java)) {
                return DetailViewModel(remoteUseCase) as T
            }else if(modelClass.isAssignableFrom(FollowerViewModel::class.java)) {
                return FollowerViewModel(remoteUseCase) as T
            }else if(modelClass.isAssignableFrom(FollowingViewModel::class.java)) {
                return FollowingViewModel(remoteUseCase) as T
            }else if(modelClass.isAssignableFrom(GithubRepositoryViewModel::class.java)) {
                return GithubRepositoryViewModel(remoteUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }

}