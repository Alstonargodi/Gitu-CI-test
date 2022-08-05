package com.example.core.data.local.source.githubapi

import com.example.core.data.local.entity.remote.userdetail.GithubUserDetail
import com.example.core.data.local.entity.remote.userfollower.GithubUserFollower
import com.example.core.data.local.entity.remote.userfollowing.GithubUserFollowing
import com.example.core.data.local.entity.userlist.GithubListUser
import com.example.core.data.local.entity.remote.userproject.GithubUserProject
import kotlinx.coroutines.flow.Flow

interface ILocalDataSource {
    suspend fun insertListUser(user: List<GithubListUser>)
    fun readListUser(): Flow<List<GithubListUser>>
    fun deleteListUser()
    suspend fun insertUserRepository(data : List<GithubUserProject>)
    fun readUserRepository(): Flow<List<GithubUserProject>>
    fun deleteUseRepository()
    suspend fun insertUserFollower(data : List<GithubUserFollower>)
    fun readUserFollower(): Flow<List<GithubUserFollower>>
    fun deleteUserFollower()
    suspend fun insertUserFollowing(data : List<GithubUserFollowing>)
    fun readUserFollowing(): Flow<List<GithubUserFollowing>>
    fun deleteUserFollowing()
    suspend fun insetUserDetail(data : List<GithubUserDetail>)
    fun readUserDetail(): Flow<List<GithubUserDetail>>
    fun deleteUserDetail()
}