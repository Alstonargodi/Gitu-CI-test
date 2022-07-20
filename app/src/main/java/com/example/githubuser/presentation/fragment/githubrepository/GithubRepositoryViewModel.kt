package com.example.githubuser.presentation.fragment.githubrepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.data.remote.apiresponse.coderepository.RepositoryUserResponseItem
import com.example.githubuser.domain.remote.RemoteUseCase

class GithubRepositoryViewModel(
    val repository: RemoteUseCase
) : ViewModel() {

    companion object{
        const val TAG = "RepositoryViewModel"
    }
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _errorResponse = MutableLiveData<String>()

    private val _repoResponse = MutableLiveData<List<RepositoryUserResponseItem>>()
    val repoResponse : LiveData<List<RepositoryUserResponseItem>> = _repoResponse

    fun getUserRepository(name: String): LiveData<List<RepositoryUserResponseItem>>{
        return repository.getUserRepository(name)
    }


}