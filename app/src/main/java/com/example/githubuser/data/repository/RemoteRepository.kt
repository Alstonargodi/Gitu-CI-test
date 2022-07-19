package com.example.githubuser.data.repository

import com.example.githubuser.data.remote.apiresponse.ListUserResponse
import com.example.githubuser.data.remote.apiresponse.coderepository.RepositoryUserResponse
import com.example.githubuser.data.remote.apiresponse.detail.DetailUserResponse
import com.example.githubuser.data.remote.apiresponse.follower.FollowerUserResponse
import com.example.githubuser.data.remote.apiservice.ApiService
import retrofit2.Call
import retrofit2.Callback

class RemoteRepository(
    private val apiService: ApiService
) {

    fun getListUser(userName : String): Call<ListUserResponse>{
        return apiService.getUserList(userName)
    }
    fun getUserDetail(name: String): Call<DetailUserResponse>{
        return apiService.getUserDetail(name)
    }

    fun getUserRepository(name : String): Call<RepositoryUserResponse>{
        return apiService.getUserRepo(name)
    }

    fun getUserFollowing(name: String): Call<FollowerUserResponse>{
        return apiService.getUserFollowing(name)
    }

    fun getUserFollower(name: String): Call<FollowerUserResponse>{
        return apiService.getUserFollowers(name)
    }

}