package com.example.githubuser.presentation.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.data.remote.apiresponse.*
import com.example.core.data.remote.utils.Resource
import com.example.core.domain.model.ListUser
import com.example.core.domain.remote.RemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val repository: RemoteUseCase
) :ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String> = _errorResponse

    fun getListUser(name : String): LiveData<Resource<List<ListUser>>> {
       return repository.getListUser(name).asLiveData()
    }

    fun deleteListUser(){
        repository.deleteListUser()
    }


}