package com.example.githubuser.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.model.config.ApiConfig
import com.example.githubuser.model.githubresponse.*
import com.example.githubuser.model.githubresponse.follower.FollowerResponse
import com.example.githubuser.model.githubresponse.follower.FollowerResponseItem
import com.example.githubuser.model.githubresponse.following.FollowingResponse
import com.example.githubuser.model.githubresponse.following.FollowingResponseItem
import com.example.githubuser.model.githubresponse.repository.RepoResponse
import com.example.githubuser.model.githubresponse.repository.RepoResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    private val _isloading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isloading


    private val _listresponse = MutableLiveData<List<ItemsItem>>()
    val listresponse : LiveData<List<ItemsItem>> = _listresponse
    fun getListUser(name : String){
        _isloading.value = true
        ApiConfig.getApiService().getUserList(name).enqueue(object : Callback<ListResponse>{
            override fun onResponse(call: Call<ListResponse>, response: Response<ListResponse>) {
                _isloading.value = false
                if (response.isSuccessful){
                    _listresponse.value = response.body()?.items
                }else{
                    Log.d("tag", response.message())
                }
            }
            override fun onFailure(call: Call<ListResponse>, t: Throwable) {

            }
        })
    }


    private val _detailResponse = MutableLiveData<DetailResponse>()
    val detailResponse : LiveData<DetailResponse> = _detailResponse
    fun getUserDetail(name : String){
        _isloading.value = true
        ApiConfig.getApiService().getUserDetail(name).enqueue(object : Callback<DetailResponse>{
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                _isloading.value = false
                if (response.isSuccessful){
                    _detailResponse.value = response.body()
                }else{
                    Log.d("tag", response.message())
                }
            }
            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {

            }
        })
    }


    private val _followersResponse = MutableLiveData<List<FollowerResponseItem>>()
    val followerResponse : LiveData<List<FollowerResponseItem>> = _followersResponse
    fun getListFollowers(name: String){
        _isloading.value = true
        ApiConfig.getApiService().getUserFollowers(name).enqueue(object : Callback<FollowerResponse>{
            override fun onResponse(
                call: Call<FollowerResponse>,
                response: Response<FollowerResponse>
            ) {
                if (response.isSuccessful){
                    _followersResponse.value = response.body()
                }else{
                    Log.d("tag", "Noresponse")
                }
            }
            override fun onFailure(call: Call<FollowerResponse>, t: Throwable) {

            }
        })
    }

    private val _followingResponse = MutableLiveData<List<FollowingResponseItem>>()
    val followingResponse : LiveData<List<FollowingResponseItem>> = _followingResponse
    fun getListFollowing(name : String){
        _isloading.value = true
        ApiConfig.getApiService().getUserFollowing(name).enqueue(object : Callback<FollowingResponse>{
            override fun onResponse(
                call: Call<FollowingResponse>,
                response: Response<FollowingResponse>
            ) {
                if (response.isSuccessful){
                    _isloading.value = false
                    _followingResponse.value = response.body()
                }else{
                    Log.d("tag", "Noresponse")
                }
            }
            override fun onFailure(call: Call<FollowingResponse>, t: Throwable) {

            }
        })

    }

    private val _repoResponse = MutableLiveData<List<RepoResponseItem>>()
    val repoResponse : LiveData<List<RepoResponseItem>> = _repoResponse
    fun getUserRepo(name: String){
        _isloading.value = true
        ApiConfig.getApiService().getUserRepo(name).enqueue(object :Callback<RepoResponse>{
            override fun onResponse(call: Call<RepoResponse>, response: Response<RepoResponse>) {
                if (response.isSuccessful){
                    _repoResponse.value = response.body()
                }else{
                    Log.d("tag", "Noresponse")
                }
            }

            override fun onFailure(call: Call<RepoResponse>, t: Throwable) {

            }
        })
    }



}