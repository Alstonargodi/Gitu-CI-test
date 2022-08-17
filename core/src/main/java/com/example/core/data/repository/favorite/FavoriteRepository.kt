package com.example.core.data.repository.favorite

import android.util.Log
import com.example.core.data.local.entity.favorite.favoriteproject.FavoriteProject
import com.example.core.data.local.entity.favorite.favoriteuser.FavoriteUser
import com.example.core.data.local.source.favorite.FavoriteDataSource
import com.example.core.data.local.source.githubapi.LocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteRepository @Inject constructor(
    private val favoriteDataSource: FavoriteDataSource
): IFavoriteRepository {
    override fun insertFavoriteProject(favoriteProject: FavoriteProject) {
        favoriteDataSource.insertFavoriteProject(favoriteProject)
    }

    override fun insertFavoritePeople(githubListUser: FavoriteUser) {
        favoriteDataSource.insertFavoritePeople(githubListUser)
    }

    override fun readFavoriteProject(): Flow<List<FavoriteProject>> =
        favoriteDataSource.readFavoriteProject()

    override fun readFavoritePeople(): Flow<List<FavoriteUser>> =
        favoriteDataSource.readFavoritePeople()

    override fun searchFavoritePeople(name: String): Flow<List<FavoriteUser>> =
        favoriteDataSource.searchFavoritePeople(name)

    override fun deleteFavoriteProject(favoriteProject: FavoriteProject) {
        favoriteDataSource.deleteFavoriteProject(favoriteProject)
    }

    override fun deletePersonFavoritePeople(name: String) {
        favoriteDataSource.deletePersonFavoritePeople(name)
    }

    override fun deleteUseRepository() {
        favoriteDataSource.deleteUseRepository()
    }

    override fun deleteUserFollower() {
        favoriteDataSource.deleteUserFollower()
    }

    override fun deleteUserFollowing() {
        favoriteDataSource.deleteUserFollowing()
    }

    override fun deleteUserDetail() {
        favoriteDataSource.deleteUserDetail()
    }

}