package com.example.core.data.local.source.githubapi

import com.example.core.data.local.entity.userlist.GithubUserList
import kotlinx.coroutines.flow.Flow

interface ILocalDataSource {
    suspend fun insertListUser(user: List<GithubUserList>)
    fun readListUser(): Flow<List<GithubUserList>>
}