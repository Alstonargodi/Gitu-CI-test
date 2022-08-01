package com.example.githubuser.presentation.fragment.follower

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.data.remote.apiresponse.follower.FollowerUserResponseItem
import com.example.core.domain.remote.RemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FollowerViewModel @Inject constructor(
    val repository: RemoteUseCase
): ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String> = _errorResponse

    private val _followersResponse = MutableLiveData<List<FollowerUserResponseItem>>()

    fun getListFollowers(name: String): LiveData<List<FollowerUserResponseItem>>{
        return repository.getUserFollower(name)
    }
}