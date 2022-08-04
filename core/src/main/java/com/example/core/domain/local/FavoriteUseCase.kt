package com.example.core.domain.local

import androidx.lifecycle.LiveData
import com.example.core.data.local.entity.favorite.favoriteproject.FavoriteProject
import com.example.core.data.local.entity.favorite.favoriteuser.FavoriteUser

interface FavoriteUseCase {
    fun insertFavoriteProject(favoriteProject: FavoriteProject)
    fun insertFavoritePeople(githubListUser: FavoriteUser)
    fun readFavoriteProject(): LiveData<List<FavoriteProject>>
    fun readFavoritePeople(): LiveData<List<FavoriteUser>>
    fun searchFavoritePeople(name : String): LiveData<List<FavoriteUser>>
    fun deleteFavoriteProject(favoriteProject: FavoriteProject)
    fun deletePersonFavoritePeople(name: String)
}