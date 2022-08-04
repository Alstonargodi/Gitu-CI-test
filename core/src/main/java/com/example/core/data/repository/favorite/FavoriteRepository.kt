package com.example.core.data.repository.favorite

import androidx.lifecycle.LiveData
import com.example.core.data.local.entity.favorite.favoriteproject.FavoriteProject
import com.example.core.data.local.entity.favorite.favoriteuser.FavoriteUser
import com.example.core.data.local.source.favorite.FavoriteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteRepository @Inject constructor(
    private val dataSource: FavoriteDataSource
): IFavoriteRepository {
    override fun insertFavoriteProject(favoriteProject: FavoriteProject) {
        dataSource.insertFavoriteProject(favoriteProject)
    }

    override fun insertFavoritePeople(githubListUser: FavoriteUser) {
        dataSource.insertFavoritePeople(githubListUser)
    }

    override fun readFavoriteProject(): LiveData<List<FavoriteProject>> =
        dataSource.readFavoriteProject()

    override fun readFavoritePeople(): LiveData<List<FavoriteUser>> =
        dataSource.readFavoritePeople()

    override fun searchFavoritePeople(name: String): LiveData<List<FavoriteUser>> =
        dataSource.searchFavoritePeople(name)

    override fun deleteFavoriteProject(favoriteProject: FavoriteProject) {
        dataSource.deleteFavoriteProject(favoriteProject)
    }

    override fun deletePersonFavoritePeople(name: String) {
        dataSource.deletePersonFavoritePeople(name)
    }

}