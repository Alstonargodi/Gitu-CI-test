package com.example.core.data.repository.favorite

import androidx.lifecycle.LiveData
import com.example.core.data.local.entity.userlist.GithubUserList
import com.example.core.data.local.entity.githubrepository.GithubRepositoryList
import com.example.core.data.local.source.favorite.FavoriteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteRepository @Inject constructor(
    private val dataSource: FavoriteDataSource
): IFavoriteRepository {
    override fun insertFavoriteProject(githubRepositoryList: GithubRepositoryList) {
        dataSource.insertFavoriteProject(githubRepositoryList)
    }

    override fun insertFavoritePeople(githubUserList: GithubUserList) {
        dataSource.insertFavoritePeople(githubUserList)
    }

    override fun readFavoriteProject(): LiveData<List<GithubRepositoryList>> =
        dataSource.readFavoriteProject()

    override fun readFavoritePeople(): LiveData<List<GithubUserList>> =
        dataSource.readFavoritePeople()

    override fun searchFavoritePeople(name: String): LiveData<List<GithubUserList>> =
        dataSource.searchFavoritePeople(name)

    override fun deleteFavoriteProject(githubRepositoryList: GithubRepositoryList) {
        dataSource.deleteFavoriteProject(githubRepositoryList)
    }

    override fun deletePersonFavoritePeople(name: String) {
        dataSource.deletePersonFavoritePeople(name)
    }

}