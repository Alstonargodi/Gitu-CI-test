package com.example.githubuser.presentation.fragment.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubuser.data.remote.apiconfig.ApiConfig
import com.example.githubuser.data.remote.apiresponse.*
import com.example.githubuser.data.repository.RemoteRepository
import com.example.githubuser.domain.remote.RemoteUseCase
import com.example.githubuser.presentation.utils.EventText.errorTitle
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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


    fun getListUser(name : String){
        _isLoading.value = true
        viewModelScope.launch {
            repository.getListUser(name).enqueue(object : Callback<ListUserResponse>{
                override fun onResponse(call: Call<ListUserResponse>, response: Response<ListUserResponse>) {
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
                override fun onFailure(call: Call<ListUserResponse>, t: Throwable) {
                    _isLoading.value = false
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                    _errorResponse.value = errorTitle + t.message.toString()
                }
            })
        }

    }


}