package com.example.githubuser.presentation.fragment.follower

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.data.remote.apiconfig.ApiConfig
import com.example.githubuser.data.remote.apiresponse.follower.FollowerUserResponse
import com.example.githubuser.data.remote.apiresponse.follower.FollowerUserResponseItem
import com.example.githubuser.data.repository.RemoteRepository
import com.example.githubuser.presentation.utils.EventText.errorTitle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerViewModel(
    val repository: RemoteRepository
): ViewModel() {
    companion object{
        const val TAG = "FollowerViewModel"
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading


    private val _errorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String> = _errorResponse

    private val _followersResponse = MutableLiveData<List<FollowerUserResponseItem>>()
    val followResponse : LiveData<List<FollowerUserResponseItem>> = _followersResponse
    fun getListFollowers(name: String){
        _isLoading.value = true
        repository.getUserFollower(name).enqueue(object :
            Callback<FollowerUserResponse> {
            override fun onResponse(call: Call<FollowerUserResponse>, response: Response<FollowerUserResponse>) {
                if (response.isSuccessful){
                    _followersResponse.value = response.body()
                    _isLoading.value = false
                }else{
                    _isLoading.value = false
                    Log.d(TAG, response.message().toString())
                    _errorResponse.value = errorTitle + response.message()
                }
            }
            override fun onFailure(call: Call<FollowerUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                _errorResponse.value = errorTitle + t.message.toString()
            }
        })
    }
}