package com.example.githubuser.data.repository.favorite

import androidx.lifecycle.LiveData
import com.example.githubuser.data.local.entity.userlist.GithubListUser
import com.example.githubuser.data.local.entity.favoriteproject.FavoriteProject
import com.example.githubuser.data.local.source.IFavoriteDataSource

class FavoriteRepository(
    private val dataSource: IFavoriteDataSource
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