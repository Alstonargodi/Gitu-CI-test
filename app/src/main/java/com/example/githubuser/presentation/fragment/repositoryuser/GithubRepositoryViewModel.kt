package com.example.githubuser.presentation.fragment.repositoryuser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.data.local.entity.githubrepository.GithubRepositoryList
import com.example.core.data.remote.apiresponse.coderepository.RepositoryUserResponseItem
import com.example.core.domain.local.FavoriteUseCase
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

    fun getUserRepository(name: String): LiveData<List<RepositoryUserResponseItem>>{
        return remote.getUserRepository(name)
    }

    fun insertFavoriteRepo(githubRepositoryList: GithubRepositoryList){
        favorite.insertFavoriteProject(githubRepositoryList)
    }


}