package com.example.githubuser.core.data.local.source

import com.example.githubuser.core.data.local.entity.userlist.GithubListUser
import kotlinx.coroutines.flow.Flow

interface ILocalDataSource {
    suspend fun insertListUser(user: List<GithubListUser>)
    fun readListUser(): Flow<List<GithubListUser>>
}