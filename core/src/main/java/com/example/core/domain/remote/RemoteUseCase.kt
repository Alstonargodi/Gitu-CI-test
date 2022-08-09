package com.example.core.domain.remote

import com.example.core.data.remote.utils.Resource
import com.example.core.domain.model.*
import kotlinx.coroutines.flow.Flow

interface RemoteUseCase {
    fun getListUser(userName : String): Flow<Resource<List<ListUser>>>
    fun showHistoryListUser(): Flow<List<ListUser>>
    fun getUserDetail(name: String): Flow<Resource<List<UserDetail>>>
    fun getUserRepository(name : String): Flow<Resource<List<UserRepository>>>
    fun getUserFollowing(name: String): Flow<Resource<List<UserFollowing>>>
    fun getUserFollower(name: String): Flow<Resource<List<UserFollower>>>
    fun deleteUserList()
    fun deleteUserRepository()
    fun deleteUserFollower()
    fun deleteUserFollowing()
    fun deleteUserDetail()
    fun updateFavoriteUser(data : UserDetail, isSaved : Boolean)
}