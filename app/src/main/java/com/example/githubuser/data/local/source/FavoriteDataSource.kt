package com.example.githubuser.data.local.source

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.githubuser.data.local.database.FavoriteDatabase
import com.example.githubuser.data.local.entity.userlist.GithubListUser
import com.example.githubuser.data.local.entity.favoriteproject.FavoriteProject
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteDataSource(val context : Context): IFavoriteDataSource {
    private val favoriteDao = FavoriteDatabase.setDatabase(context).favoriteDao()
    private val executorService : ExecutorService = Executors.newSingleThreadExecutor()

    override fun insertFavoriteProject(favoriteProject: FavoriteProject) {
        executorService.execute { favoriteDao.insertFavoriteProject(favoriteProject) }
    }

    override fun insertFavoritePeople(githubListUser: GithubListUser) {
        executorService.execute { favoriteDao.insertFavoritePeople(githubListUser) }
    }

    override fun readFavoriteProject(): LiveData<List<FavoriteProject>> =
        favoriteDao.readFavoriteProject()

    override fun readFavoritePeople(): LiveData<List<GithubListUser>> =
        favoriteDao.readFavoritePeople()

    override fun searchFavoritePeople(name: String): LiveData<List<GithubListUser>> =
        favoriteDao.searchFavoritePeople(name)

    override fun deleteFavoriteProject(favoriteProject: FavoriteProject) {
        favoriteDao.deleteFavoriteProject(favoriteProject)
    }

    override fun deletePersonFavoritePeople(name: String) {
        favoriteDao.deletePersonFavoritePeople(name)
    }

}