package com.example.core.data.local.source.favorite

import androidx.lifecycle.LiveData
import com.example.core.data.local.entity.favorite.favoriteproject.FavoriteProject
import com.example.core.data.local.entity.favorite.favoriteuser.FavoriteUser
import kotlinx.coroutines.flow.Flow

interface IFavoriteDataSource {
    fun insertFavoriteProject(favoriteProject: FavoriteProject)
    fun insertFavoritePeople(githubListUser: FavoriteUser)
    fun readFavoriteProject(): Flow<List<FavoriteProject>>
    fun readFavoritePeople():Flow<List<FavoriteUser>>
    fun searchFavoritePeople(name : String):Flow<List<FavoriteUser>>
    fun deleteFavoriteProject(favoriteProject: FavoriteProject)
    fun deletePersonFavoritePeople(name: String)
    fun deleteUseRepository()
    fun deleteUserFollower()
    fun deleteUserFollowing()
    fun deleteUserDetail()
}