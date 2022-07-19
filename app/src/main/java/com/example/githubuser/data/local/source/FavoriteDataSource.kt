package com.example.githubuser.data.local.source

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.githubuser.data.local.database.FavoriteDatabase
import com.example.githubuser.data.local.entity.favoritepeople.FavoritePeople
import com.example.githubuser.data.local.entity.favoriteproject.FavoriteProject
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteDataSource(val context : Context): IFavoriteDataSource {
    val favoriteDao = FavoriteDatabase.setDatabase(context).favoritedao()
    private val executorService : ExecutorService = Executors.newSingleThreadExecutor()

    override fun insertFavoriteProject(favoriteProject: FavoriteProject) {
        executorService.execute { favoriteDao.insertFavoriteProject(favoriteProject) }
    }

    override fun insertFavoritePeople(favoritePeople: FavoritePeople) {
        executorService.execute { favoriteDao.insertFavoritePeople(favoritePeople) }
    }

    override fun readFavoriteProject(): LiveData<List<FavoriteProject>> =
        favoriteDao.readFavoriteProject()

    override fun readFavoritePeople(): LiveData<List<FavoritePeople>> =
        favoriteDao.readFavoritePeople()

    override fun searchFavoritePeople(name: String): LiveData<List<FavoritePeople>> =
        favoriteDao.searchFavoritePeople(name)

    override fun deleteFavoriteProject(favoriteProject: FavoriteProject) {
        favoriteDao.deleteFavoriteProject(favoriteProject)
    }

    override fun deletePersonFavoritePeople(name: String) {
        favoriteDao.deletePersonFavoritePeople(name)
    }

}