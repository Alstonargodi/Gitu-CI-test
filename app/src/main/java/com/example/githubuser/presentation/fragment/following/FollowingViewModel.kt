package com.example.githubuser.presentation.fragment.following

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.data.remote.utils.Resource
import com.example.core.domain.model.UserFollowing
import com.example.core.domain.remote.RemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FollowingViewModel @Inject constructor(
    private val remoteUserCase: RemoteUseCase
): ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String> = _errorResponse

    fun getListFollowing(name : String): LiveData<Resource<List<UserFollowing>>>{
        return remoteUserCase.getUserFollowing(name).asLiveData()
    }

    fun deleteUserFollowing(){
        remoteUserCase.deleteUserFollowing()
    }

    fun deleteUserRepository(){
       remoteUserCase.deleteUserRepository()
    }
}