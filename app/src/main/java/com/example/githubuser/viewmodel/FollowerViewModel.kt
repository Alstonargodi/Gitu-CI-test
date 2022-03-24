package com.example.githubuser.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.data.remote.config.ApiConfig
import com.example.githubuser.data.remote.githubresponse.follow.FollowResponse
import com.example.githubuser.data.remote.githubresponse.follow.FollowResponseItem
import com.example.githubuser.view.utils.EventText.errorTitle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerViewModel: ViewModel() {
    companion object{
        const val TAG = "FollowerViewModel"
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading


    private val _errorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String> = _errorResponse

    private val _followersResponse = MutableLiveData<List<FollowResponseItem>>()
    val followResponse : LiveData<List<FollowResponseItem>> = _followersResponse
    fun getListFollowers(name: String){
        _isLoading.value = true
        ApiConfig.getApiService().getUserFollowers(name).enqueue(object :
            Callback<FollowResponse> {
            override fun onResponse(call: Call<FollowResponse>, response: Response<FollowResponse>) {
                if (response.isSuccessful){
                    _followersResponse.value = response.body()
                    _isLoading.value = false
                }else{
                    _isLoading.value = false
                    Log.d(TAG, response.message().toString())
                    _errorResponse.value = errorTitle + response.message()
                }
            }
            override fun onFailure(call: Call<FollowResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                _errorResponse.value = errorTitle + t.message.toString()
            }
        })
    }
}