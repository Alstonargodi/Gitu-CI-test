package com.example.githubuser.presentation.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.data.remote.apiresponse.*
import com.example.githubuser.domain.remote.RemoteUseCase

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


    fun getListUser(name : String):LiveData<List<ListUserResponseItem>>{
       return repository.getListUser(name)
    }


}