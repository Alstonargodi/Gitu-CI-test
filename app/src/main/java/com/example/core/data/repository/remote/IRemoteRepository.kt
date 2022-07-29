package com.example.core.data.repository.remote

import com.example.core.data.remote.apiresponse.coderepository.RepositoryUserResponse
import com.example.core.data.remote.apiresponse.detail.DetailUserResponse
import com.example.core.data.remote.apiresponse.follower.FollowerUserResponse
import com.example.core.data.remote.utils.Resource
import com.example.core.domain.model.ListUser
import kotlinx.coroutines.flow.Flow
import retrofit2.Call

interface IRemoteRepository {
    fun getListUser(userName : String): Flow<Resource<List<ListUser>>>
    fun getUserDetail(name: String): Call<DetailUserResponse>
    fun getUserRepository(name : String): Call<RepositoryUserResponse>
    fun getUserFollowing(name: String): Call<FollowerUserResponse>
    fun getUserFollower(name: String): Call<FollowerUserResponse>
}