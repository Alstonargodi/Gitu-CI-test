package com.example.githubuser.presentation.fragment.following

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.data.remote.apiconfig.ApiConfig
import com.example.githubuser.data.remote.apiresponse.follower.FollowerUserResponse
import com.example.githubuser.data.remote.apiresponse.follower.FollowerUserResponseItem
import com.example.githubuser.data.repository.FavoriteRepository
import com.example.githubuser.data.repository.RemoteRepository
import com.example.githubuser.presentation.utils.EventText.errorTitle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel(
    val repository: RemoteRepository
): ViewModel() {
    companion object{
        const val TAG = "FollowingViewModel"
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String> = _errorResponse

    private val _followingResponse = MutableLiveData<List<FollowerUserResponseItem>>()
    val followingResponse : LiveData<List<FollowerUserResponseItem>> = _followingResponse

    fun getListFollowing(name : String){
        _isLoading.value = true
        repository.getUserFollowing(name).enqueue(object : Callback<FollowerUserResponse> {
            override fun onResponse(call: Call<FollowerUserResponse>, response: Response<FollowerUserResponse>) {
                _isLoading.value = false
                if (response.isSuccessful){
                    _isLoading.value = false
                    _followingResponse.value = response.body()
                }else{
                    Log.d(TAG, response.message())
                    _errorResponse.value = errorTitle + response.body()
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