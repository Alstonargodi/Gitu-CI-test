package com.example.githubuser.presentation.fragment.following

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.data.remote.apiresponse.follower.FollowerUserResponseItem
import com.example.githubuser.domain.remote.RemoteUseCase

class FollowingViewModel(
    val repository: RemoteUseCase
): ViewModel() {
    companion object{
        const val TAG = "FollowingViewModel"
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String> = _errorResponse

    private val _followingResponse = MutableLiveData<List<FollowerUserResponseItem>>()
    val followingResponse : LiveData<List<FollowerUserResponseItem>> = _followingResponse

    fun getListFollowing(name : String): LiveData<List<FollowerUserResponseItem>>{
        return repository.getUserFollowing(name)
    }
}