package com.example.core.data.local.source.favorite

import androidx.lifecycle.LiveData
import com.example.core.data.local.dao.FavoriteDao
import com.example.core.data.local.entity.userlist.GithubUserList
import com.example.core.data.local.entity.githubrepository.GithubRepositoryList
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteDataSource @Inject constructor(
    private val favoriteDao : FavoriteDao
): IFavoriteDataSource {
    private val executorService : ExecutorService = Executors.newSingleThreadExecutor()

    override fun insertFavoriteProject(githubRepositoryList: GithubRepositoryList) {
        executorService.execute { favoriteDao.insertFavoriteProject(githubRepositoryList) }
    }

    override fun insertFavoritePeople(githubUserList: GithubUserList) {
        executorService.execute { favoriteDao.insertFavoritePeople(githubUserList) }
    }

    override fun readFavoriteProject(): LiveData<List<GithubRepositoryList>> =
        favoriteDao.readFavoriteProject()

    override fun readFavoritePeople(): LiveData<List<GithubUserList>> =
        favoriteDao.readFavoritePeople()

    override fun searchFavoritePeople(name: String): LiveData<List<GithubUserList>> =
        favoriteDao.searchFavoritePeople(name)

    override fun deleteFavoriteProject(githubRepositoryList: GithubRepositoryList) {
        favoriteDao.deleteFavoriteProject(githubRepositoryList)
    }

    override fun deletePersonFavoritePeople(name: String) {
        favoriteDao.deletePersonFavoritePeople(name)
    }

}