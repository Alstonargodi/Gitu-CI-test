package com.example.core.domain.remote

import androidx.lifecycle.LiveData
import com.example.core.data.remote.apiresponse.coderepository.RepositoryUserResponseItem
import com.example.core.data.remote.apiresponse.detail.DetailUserResponse
import com.example.core.data.remote.apiresponse.follower.FollowerUserResponseItem
import com.example.core.data.remote.utils.Resource
import com.example.core.domain.model.ListUser
import kotlinx.coroutines.flow.Flow

interface RemoteUseCase {
    fun getListUser(userName : String): Flow<Resource<List<ListUser>>>
    fun showHistoryListUser(): Flow<List<ListUser>>
    fun getUserDetail(name: String): LiveData<DetailUserResponse>
    fun getUserRepository(name : String): LiveData<List<RepositoryUserResponseItem>>
    fun getUserFollowing(name: String): LiveData<List<FollowerUserResponseItem>>
    fun getUserFollower(name: String): LiveData<List<FollowerUserResponseItem>>
    fun deleteListUser()
}