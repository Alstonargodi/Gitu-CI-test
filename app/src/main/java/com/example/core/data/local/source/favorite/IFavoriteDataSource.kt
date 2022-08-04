package com.example.core.data.local.source.favorite

import androidx.lifecycle.LiveData
import com.example.core.data.local.entity.userlist.GithubListUser
import com.example.core.data.local.entity.favoriteproject.FavoriteProject
import com.example.core.data.local.entity.favoriteuser.FavoriteUser

interface IFavoriteDataSource {
    fun insertFavoriteProject(favoriteProject: FavoriteProject)
    fun insertFavoritePeople(githubListUser: FavoriteUser)
    fun readFavoriteProject(): LiveData<List<FavoriteProject>>
    fun readFavoritePeople():LiveData<List<FavoriteUser>>
    fun searchFavoritePeople(name : String):LiveData<List<FavoriteUser>>
    fun deleteFavoriteProject(favoriteProject: FavoriteProject)
    fun deletePersonFavoritePeople(name: String)
}