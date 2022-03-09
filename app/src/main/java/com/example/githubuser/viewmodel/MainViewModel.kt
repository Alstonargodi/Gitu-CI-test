package com.example.githubuser.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubuser.remote.config.ApiConfig
import com.example.githubuser.remote.githubresponse.*
import com.example.githubuser.view.utils.EventText.errorTitle
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    companion object{
        const val TAG = "MainViewModel"
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading


    private val _errorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String> = _errorResponse


    /*
    main viewmodel
    */
    private val _listresponse = MutableLiveData<List<ItemsItem>>()
    val listresponse : LiveData<List<ItemsItem>> = _listresponse
    fun getListUser(name : String){
        _isLoading.value = true
        viewModelScope.launch {
            ApiConfig.getApiService().getUserList(name).enqueue(object : Callback<ListResponse>{
                override fun onResponse(call: Call<ListResponse>, response: Response<ListResponse>) {
                    if (response.isSuccessful){
                        _listresponse.postValue(response.body()?.items)
                        _isLoading.value = false
                        _errorResponse.value = ""
                    }else{
                        Log.d(TAG, response.message().toString())
                        _errorResponse.value = errorTitle + response.message()
                        _isLoading.value = false
                    }
                }
                override fun onFailure(call: Call<ListResponse>, t: Throwable) {
                    _isLoading.value = false
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                    _errorResponse.value = errorTitle + t.message.toString()
                }
            })
        }

    }



}