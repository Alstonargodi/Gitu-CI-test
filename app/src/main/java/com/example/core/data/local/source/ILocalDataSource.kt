package com.example.core.data.local.source

import com.example.core.data.local.entity.userlist.GithubListUser
import kotlinx.coroutines.flow.Flow

interface ILocalDataSource {
    suspend fun insertListUser(user: List<GithubListUser>)
    fun readListUser(): Flow<List<GithubListUser>>
}