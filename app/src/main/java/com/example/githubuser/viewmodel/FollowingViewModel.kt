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

class FollowingViewModel: ViewModel() {
    companion object{
        const val TAG = "FollowingViewModel"
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String> = _errorResponse

    private val _followingResponse = MutableLiveData<List<FollowResponseItem>>()
    val followingResponse : LiveData<List<FollowResponseItem>> = _followingResponse

    fun getListFollowing(name : String){
        _isLoading.value = true
        ApiConfig.getApiService().getUserFollowing(name).enqueue(object :
            Callback<FollowResponse> {
            override fun onResponse(call: Call<FollowResponse>, response: Response<FollowResponse>) {
                _isLoading.value = false
                if (response.isSuccessful){
                    _isLoading.value = false
                    _followingResponse.value = response.body()
                }else{
                    Log.d(TAG, response.message())
                    _errorResponse.value = errorTitle + response.body()
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