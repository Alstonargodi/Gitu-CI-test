package com.example.githubuser.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.data.remote.config.ApiConfig
import com.example.githubuser.data.remote.githubresponse.DetailResponse
import com.example.githubuser.view.utils.EventText.errorTitle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {
    companion object{
        const val TAG = "DetailViewModel"
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String> = _errorResponse

    private val _detailResponse = MutableLiveData<DetailResponse>()
    val detailResponse : LiveData<DetailResponse> = _detailResponse
    fun getUserDetail(name : String){
        _isLoading.value = true
        ApiConfig.getApiService().getUserDetail(name).enqueue(object : Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                if (response.isSuccessful){
                    _detailResponse.postValue(response.body())
                    _isLoading.value = false
                    _errorResponse.value = ""
                }else{
                    Log.d(TAG, response.message().toString())
                    _errorResponse.value = errorTitle + response.message()
                    _isLoading.value = false
                }
            }
            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                _errorResponse.value =  errorTitle + t.message.toString()
            }
        })
    }
}