package com.example.githubuser.presentation.fragment.follower

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.data.remote.utils.Resource
import com.example.core.domain.model.UserFollower
import com.example.core.domain.remote.RemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FollowerViewModel @Inject constructor(
    private val remoteUseCase: RemoteUseCase
): ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String> = _errorResponse

    fun getListFollowers(name: String): LiveData<Resource<List<UserFollower>>>{
        return remoteUseCase.getUserFollower(name).asLiveData()
    }

    fun deleteUserFollower(){
        remoteUseCase.deleteUserFollower()
    }

    fun deleteUserRepository(){
        remoteUseCase.deleteUserRepository()
    }
}