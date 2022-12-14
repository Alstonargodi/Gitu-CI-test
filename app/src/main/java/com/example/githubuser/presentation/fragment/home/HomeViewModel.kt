package com.example.githubuser.presentation.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.data.remote.utils.Resource
import com.example.core.domain.model.ListUser
import com.example.core.domain.remote.RemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val remoteUseCase: RemoteUseCase
) :ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading


    fun getListUser(name : String): LiveData<Resource<List<ListUser>>> {
       return remoteUseCase.getListUser(name).asLiveData()
    }

    fun showHistoryListUser() : LiveData<List<ListUser>> =
        remoteUseCase.showHistoryListUser().asLiveData()

    fun deleteListUser(){
        remoteUseCase.deleteUserList()
    }

    fun clearDetail(){
        remoteUseCase.apply {
            deleteUserFollower()
            deleteUserDetail()
            deleteUserFollowing()
            deleteUserRepository()
        }
    }
}