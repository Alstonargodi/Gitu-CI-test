package com.example.githubuser.presentation.fragment.githubrepository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.data.remote.apiconfig.ApiConfig
import com.example.githubuser.data.remote.apiresponse.coderepository.RepositoryUserResponse
import com.example.githubuser.data.remote.apiresponse.coderepository.RepositoryUserResponseItem
import com.example.githubuser.data.repository.RemoteRepository
import com.example.githubuser.presentation.utils.EventText.errorTitle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubRepositoryViewModel(
    val repository: RemoteRepository
) : ViewModel() {

    companion object{
        const val TAG = "RepositoryViewModel"
    }
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _errorResponse = MutableLiveData<String>()

    private val _repoResponse = MutableLiveData<List<RepositoryUserResponseItem>>()
    val repoResponse : LiveData<List<RepositoryUserResponseItem>> = _repoResponse
    fun getUserRepo(name: String){
        _isLoading.value = true
        repository.getUserRepository(name).enqueue(object : Callback<RepositoryUserResponse> {
            override fun onResponse(call: Call<RepositoryUserResponse>, response: Response<RepositoryUserResponse>) {
                if (response.isSuccessful){
                    _repoResponse.value = response.body()
                    _isLoading.value = false
                }else{
                    Log.d(TAG, response.message())
                    _errorResponse.value = errorTitle + response.message()
                    _isLoading.value = false
                }
            }

            override fun onFailure(call: Call<RepositoryUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                _errorResponse.value = errorTitle + t.message.toString()
            }
        })
    }



}