package com.example.githubuser.data.remote.source

import com.example.githubuser.data.remote.apiconfig.ApiConfig
import com.example.githubuser.data.remote.apiresponse.ListUserResponse
import com.example.githubuser.data.remote.apiresponse.coderepository.RepositoryUserResponse
import com.example.githubuser.data.remote.apiresponse.detail.DetailUserResponse
import com.example.githubuser.data.remote.apiresponse.follower.FollowerUserResponse
import com.example.githubuser.data.remote.apiservice.ApiService
import retrofit2.Call

class RemoteDataSource(): IRemoteDataSource {
    private val apiService = ApiConfig.getApiService()

    override fun getListUser(userName: String): Call<ListUserResponse> =
        apiService.getUserList(userName)

    override fun getUserDetail(name: String): Call<DetailUserResponse> =
        apiService.getUserDetail(name)

    override fun getUserRepository(name: String): Call<RepositoryUserResponse> =
        apiService.getUserRepo(name)

    override fun getUserFollowing(name: String): Call<FollowerUserResponse> =
        apiService.getUserFollowing(name)

    override fun getUserFollower(name: String): Call<FollowerUserResponse> =
        apiService.getUserFollowers(name)

}