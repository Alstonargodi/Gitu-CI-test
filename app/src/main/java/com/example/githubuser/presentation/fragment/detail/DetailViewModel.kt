package com.example.githubuser.presentation.fragment.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.data.remote.apiconfig.ApiConfig
import com.example.githubuser.data.remote.apiresponse.detail.DetailUserResponse
import com.example.githubuser.data.repository.FavoriteRepository
import com.example.githubuser.data.repository.RemoteRepository
import com.example.githubuser.presentation.utils.EventText.errorTitle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(
    val remoteRepository: RemoteRepository
): ViewModel() {
    companion object{
        const val TAG = "DetailViewModel"
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String> = _errorResponse

    private val _detailUserResponse = MutableLiveData<DetailUserResponse>()
    val detailUserResponse : LiveData<DetailUserResponse> = _detailUserResponse
    fun getUserDetail(name : String){
        _isLoading.value = true
        remoteRepository.getUserDetail(name).enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                if (response.isSuccessful){
                    _detailUserResponse.postValue(response.body())
                    _isLoading.value = false
                    _errorResponse.value = ""
                }else{
                    Log.d(TAG, response.message().toString())
                    _errorResponse.value = errorTitle + response.message()
                    _isLoading.value = false
                }
            }
            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                _errorResponse.value =  errorTitle + t.message.toString()
            }
        })
    }
}