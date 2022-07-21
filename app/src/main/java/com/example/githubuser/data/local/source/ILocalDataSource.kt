package com.example.githubuser.data.local.source

import androidx.lifecycle.LiveData
import com.example.githubuser.data.local.entity.favoriteproject.FavoriteProject
import com.example.githubuser.data.local.entity.userlist.GithubListUser

interface ILocalDataSource {
    suspend fun insertListUser(user: GithubListUser)
    fun readListUser(): LiveData<List<GithubListUser>>
}