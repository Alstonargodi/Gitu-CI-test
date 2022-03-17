package com.example.githubuser.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.remote.config.ApiConfig
import com.example.githubuser.remote.githubresponse.repository.RepoResponse
import com.example.githubuser.remote.githubresponse.repository.RepoResponseItem
import com.example.githubuser.view.utils.EventText.errorTitle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositorViewModel : ViewModel() {
    companion object{
        const val TAG = "RepositoryViewModel"
    }
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _errorResponse = MutableLiveData<String>()

    private val _repoResponse = MutableLiveData<List<RepoResponseItem>>()
    val repoResponse : LiveData<List<RepoResponseItem>> = _repoResponse
    fun getUserRepo(name: String){
        _isLoading.value = true
        ApiConfig.getApiService().getUserRepo(name).enqueue(object : Callback<RepoResponse> {
            override fun onResponse(call: Call<RepoResponse>, response: Response<RepoResponse>) {
                if (response.isSuccessful){
                    _repoResponse.value = response.body()
                    _isLoading.value = false
                }else{
                    Log.d(TAG, response.message())
                    _errorResponse.value = errorTitle + response.message()
                    _isLoading.value = false
                }
            }

            override fun onFailure(call: Call<RepoResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                _errorResponse.value = errorTitle + t.message.toString()
            }
        })
    }



}