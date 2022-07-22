package com.example.githubuser.presentation.fragment.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.githubuser.data.remote.apiresponse.*
import com.example.githubuser.data.remote.utils.Resource
import com.example.githubuser.domain.model.ListUser
import com.example.githubuser.domain.remote.RemoteUseCase
import kotlinx.coroutines.flow.Flow

class HomeViewModel(
    val repository: RemoteUseCase
) :ViewModel() {
    companion object{
        const val TAG = "HomeViewModel"
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading


    private val _errorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String> = _errorResponse

    /*
    main viewmodel
    */
    private val _listresponse = MutableLiveData<List<ListUserResponseItem>>()
    val listresponse : LiveData<List<ListUserResponseItem>> = _listresponse


    fun getListUser(name : String): LiveData<Resource<List<ListUser>>> {
       return repository.getListUser(name).asLiveData()
    }


}