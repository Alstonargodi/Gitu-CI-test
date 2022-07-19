package com.example.githubuser.data.repository.favorite

import androidx.lifecycle.LiveData
import com.example.githubuser.data.local.entity.favoritepeople.FavoritePeople
import com.example.githubuser.data.local.entity.favoriteproject.FavoriteProject

interface IFavoriteRepository {
    fun insertFavoriteProject(favoriteProject: FavoriteProject)
    fun insertFavoritePeople(favoritePeople: FavoritePeople)
    fun readFavoriteProject(): LiveData<List<FavoriteProject>>
    fun readFavoritePeople(): LiveData<List<FavoritePeople>>
    fun searchFavoritePeople(name : String): LiveData<List<FavoritePeople>>
    fun deleteFavoriteProject(favoriteProject: FavoriteProject)
    fun deletePersonFavoritePeople(name: String)
}