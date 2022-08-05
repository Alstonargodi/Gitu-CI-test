package com.example.core.data.remote.source

import com.example.core.data.remote.apiresponse.ListUserResponse
import com.example.core.data.remote.apiresponse.coderepository.RepositoryUserResponse
import com.example.core.data.remote.apiresponse.detail.DetailUserResponse
import com.example.core.data.remote.apiresponse.follower.FollowerUserResponse
import com.example.core.data.remote.utils.FetchResults
import kotlinx.coroutines.flow.Flow
import retrofit2.Call


interface IRemoteDataSource {
    suspend fun getListUser(userName : String): Flow<FetchResults<ListUserResponse>>
    suspend fun getUserDetail(name: String):Flow<FetchResults<DetailUserResponse>>
    suspend fun getUserRepository(name : String): Flow<FetchResults<RepositoryUserResponse>>
    suspend fun getUserFollowing(name: String): Flow<FetchResults<FollowerUserResponse>>
    suspend fun getUserFollower(name: String): Flow<FetchResults<FollowerUserResponse>>
}