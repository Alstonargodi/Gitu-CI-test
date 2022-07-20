package com.example.githubuser.presentation.fragment.follower

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.data.remote.apiresponse.follower.FollowerUserResponseItem
import com.example.githubuser.domain.remote.RemoteUseCase

class FollowerViewModel(
    val repository: RemoteUseCase
): ViewModel() {
    companion object{
        const val TAG = "FollowerViewModel"
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading


    private val _errorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String> = _errorResponse

    private val _followersResponse = MutableLiveData<List<FollowerUserResponseItem>>()
    val followResponse : LiveData<List<FollowerUserResponseItem>> = _followersResponse
    fun getListFollowers(name: String): LiveData<List<FollowerUserResponseItem>>{
        return repository.getUserFollower(name)
    }
}