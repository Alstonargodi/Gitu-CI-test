package com.example.core.data.local.source

import androidx.lifecycle.LiveData
import com.example.core.data.local.entity.userlist.GithubListUser
import com.example.core.data.local.entity.favoriteproject.FavoriteProject

interface IFavoriteDataSource {
    fun insertFavoriteProject(favoriteProject: FavoriteProject)
    fun insertFavoritePeople(githubListUser: GithubListUser)
    fun readFavoriteProject(): LiveData<List<FavoriteProject>>
    fun readFavoritePeople():LiveData<List<GithubListUser>>
    fun searchFavoritePeople(name : String):LiveData<List<GithubListUser>>
    fun deleteFavoriteProject(favoriteProject: FavoriteProject)
    fun deletePersonFavoritePeople(name: String)
}