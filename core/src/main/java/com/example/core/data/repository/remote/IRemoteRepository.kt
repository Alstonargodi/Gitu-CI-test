package com.example.core.data.repository.remote

import com.example.core.data.local.entity.userlist.GithubListUser
import com.example.core.data.remote.utils.Resource
import com.example.core.domain.model.*
import kotlinx.coroutines.flow.Flow

interface IRemoteRepository {
    fun getListUser(userName : String): Flow<Resource<List<ListUser>>>
    fun showHistoryListUser(): Flow<List<ListUser>>
    fun getUserRepository(name : String): Flow<Resource<List<UserRepository>>>
    fun getUserFollower(name: String): Flow<Resource<List<UserFollower>>>
    fun getUserFollowing(name: String): Flow<Resource<List<UserFollowing>>>
    fun getUserDetail(name: String): Flow<Resource<List<UserDetail>>>

    fun deleteListUser()
    fun deleteUseRepository()
    fun deleteUserFollower()
    fun deleteUserFollowing()
    fun deleteUserDetail()

    fun updateFavoriteUser(githubListUser: UserDetail, isSaved : Boolean)
}