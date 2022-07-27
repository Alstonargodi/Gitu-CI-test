package com.example.githubuser.core.data.repository.favorite

import androidx.lifecycle.LiveData
import com.example.githubuser.core.data.local.entity.userlist.GithubListUser
import com.example.githubuser.core.data.local.entity.favoriteproject.FavoriteProject
import com.example.githubuser.core.data.local.source.FavoriteDataSource
import com.example.githubuser.core.data.local.source.IFavoriteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteRepository @Inject constructor(
    private val dataSource: FavoriteDataSource
): IFavoriteRepository {
    override fun insertFavoriteProject(favoriteProject: FavoriteProject) {
        dataSource.insertFavoriteProject(favoriteProject)
    }

    override fun insertFavoritePeople(githubListUser: GithubListUser) {
        dataSource.insertFavoritePeople(githubListUser)
    }

    override fun readFavoriteProject(): LiveData<List<FavoriteProject>> =
        dataSource.readFavoriteProject()

    override fun readFavoritePeople(): LiveData<List<GithubListUser>> =
        dataSource.readFavoritePeople()

    override fun searchFavoritePeople(name: String): LiveData<List<GithubListUser>> =
        dataSource.searchFavoritePeople(name)

    override fun deleteFavoriteProject(favoriteProject: FavoriteProject) {
        dataSource.deleteFavoriteProject(favoriteProject)
    }

    override fun deletePersonFavoritePeople(name: String) {
        dataSource.deletePersonFavoritePeople(name)
    }

}