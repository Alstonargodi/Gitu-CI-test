package com.example.core.data.local.source.favorite

import androidx.lifecycle.LiveData
import com.example.core.data.local.dao.FavoriteDao
import com.example.core.data.local.entity.userlist.GithubListUser
import com.example.core.data.local.entity.favoriteproject.FavoriteProject
import com.example.core.data.local.entity.favoriteuser.FavoriteUser
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteDataSource @Inject constructor(
    private val favoriteDao : FavoriteDao
): IFavoriteDataSource {
    private val executorService : ExecutorService = Executors.newSingleThreadExecutor()

    override fun insertFavoriteProject(favoriteProject: FavoriteProject) {
        executorService.execute { favoriteDao.insertFavoriteProject(favoriteProject) }
    }

    override fun insertFavoritePeople(githubListUser: FavoriteUser) {
        executorService.execute { favoriteDao.insertFavoritePeople(githubListUser) }
    }

    override fun readFavoriteProject(): LiveData<List<FavoriteProject>> =
        favoriteDao.readFavoriteProject()

    override fun readFavoritePeople(): LiveData<List<FavoriteUser>> =
        favoriteDao.readFavoritePeople()

    override fun searchFavoritePeople(name: String): LiveData<List<FavoriteUser>> =
        favoriteDao.searchFavoritePeople(name)

    override fun deleteFavoriteProject(favoriteProject: FavoriteProject) {
        favoriteDao.deleteFavoriteProject(favoriteProject)
    }

    override fun deletePersonFavoritePeople(name: String) {
        favoriteDao.deletePersonFavoritePeople(name)
    }

}