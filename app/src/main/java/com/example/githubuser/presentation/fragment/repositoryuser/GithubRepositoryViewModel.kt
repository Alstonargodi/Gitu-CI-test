package com.example.githubuser.presentation.fragment.repositoryuser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.data.local.entity.favorite.favoriteproject.FavoriteProject
import com.example.core.data.remote.utils.Resource
import com.example.core.domain.local.FavoriteUseCase
import com.example.core.domain.model.UserRepository
import com.example.core.domain.remote.RemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GithubRepositoryViewModel @Inject constructor(
    val remote: RemoteUseCase,
    val favorite : FavoriteUseCase
) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    fun getUserRepository(name: String): LiveData<Resource<List<UserRepository>>> {
        return remote.getUserRepository(name).asLiveData()
    }

    fun insertFavoriteRepo(favoriteProject: FavoriteProject){
        favorite.insertFavoriteProject(favoriteProject)
    }

    fun deleteUserRepository(){
        remote.deleteUserRepository()
    }


}