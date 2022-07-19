package com.example.githubuser.domain.remote

import androidx.lifecycle.LiveData
import com.example.githubuser.data.remote.apiresponse.ListUserResponse
import com.example.githubuser.data.remote.apiresponse.coderepository.RepositoryUserResponse
import com.example.githubuser.data.remote.apiresponse.detail.DetailUserResponse
import com.example.githubuser.data.remote.apiresponse.follower.FollowerUserResponse
import com.example.githubuser.data.remote.apiresponse.follower.FollowerUserResponseItem
import retrofit2.Call

interface RemoteUseCase {
    fun getListUser(userName : String)
    fun getUserDetail(name: String): Call<DetailUserResponse>
    fun getUserRepository(name : String): Call<RepositoryUserResponse>
    fun getUserFollowing(name: String): Call<FollowerUserResponse>
    fun getUserFollower(name: String): Call<FollowerUserResponse>
}