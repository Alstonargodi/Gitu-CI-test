package com.example.githubuser.core.data.remote.source

import com.example.githubuser.core.data.remote.apiresponse.ListUserResponse
import com.example.githubuser.core.data.remote.apiresponse.coderepository.RepositoryUserResponse
import com.example.githubuser.core.data.remote.apiresponse.detail.DetailUserResponse
import com.example.githubuser.core.data.remote.apiresponse.follower.FollowerUserResponse
import com.example.githubuser.core.data.remote.utils.FetchResults
import kotlinx.coroutines.flow.Flow
import retrofit2.Call


interface IRemoteDataSource {
    suspend fun getListUser(userName : String): Flow<FetchResults<ListUserResponse>>
    fun getUserDetail(name: String): Call<DetailUserResponse>
    fun getUserRepository(name : String): Call<RepositoryUserResponse>
    fun getUserFollowing(name: String): Call<FollowerUserResponse>
    fun getUserFollower(name: String): Call<FollowerUserResponse>
}