package com.example.core.data.local.source.githubapi

import com.example.core.data.local.entity.remote.following.GithubUserFollower
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
}