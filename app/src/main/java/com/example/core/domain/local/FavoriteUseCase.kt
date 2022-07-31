package com.example.core.domain.local

import androidx.lifecycle.LiveData
import com.example.core.data.local.entity.userlist.GithubUserList
import com.example.core.data.local.entity.githubrepository.GithubRepositoryList

interface FavoriteUseCase {
    fun insertFavoriteProject(githubRepositoryList: GithubRepositoryList)
    fun insertFavoritePeople(githubUserList: GithubUserList)
    fun readFavoriteProject(): LiveData<List<GithubRepositoryList>>
    fun readFavoritePeople(): LiveData<List<GithubUserList>>
    fun searchFavoritePeople(name : String): LiveData<List<GithubUserList>>
    fun deleteFavoriteProject(githubRepositoryList: GithubRepositoryList)
    fun deletePersonFavoritePeople(name: String)
}