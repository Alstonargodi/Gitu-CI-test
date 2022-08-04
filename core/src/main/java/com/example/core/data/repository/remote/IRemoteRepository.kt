package com.example.core.data.repository.remote

import com.example.core.data.remote.apiresponse.detail.DetailUserResponse
import com.example.core.data.remote.apiresponse.follower.FollowerUserResponse
import com.example.core.data.remote.utils.Resource
import com.example.core.domain.model.ListUser
import com.example.core.domain.model.UserFollower
import com.example.core.domain.model.UserRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Call

interface IRemoteRepository {
    fun getListUser(userName : String): Flow<Resource<List<ListUser>>>
    fun showHistoryListUser(): Flow<List<ListUser>>
    fun getUserRepository(name : String): Flow<Resource<List<UserRepository>>>
    fun getUserFollower(name: String): Flow<Resource<List<UserFollower>>>

    fun getUserDetail(name: String): Call<DetailUserResponse>
    fun getUserFollowing(name: String): Call<FollowerUserResponse>

    fun deleteListUser()
    fun deleteUseRepository()
    fun deleteUserFollower()
}