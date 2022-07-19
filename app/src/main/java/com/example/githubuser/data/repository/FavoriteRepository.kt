package com.example.githubuser.data.repository

import androidx.lifecycle.LiveData
import com.example.githubuser.data.local.database.FavoriteDatabase
import com.example.githubuser.data.local.entity.favoritepeople.FavoritePeople
import com.example.githubuser.data.local.entity.favoriteproject.FavoriteProject
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(
    database: FavoriteDatabase
) {
    private val favoriteDao = database.favoritedao()
    private val executorService : ExecutorService = Executors.newSingleThreadExecutor()

    fun readFavoriteProject(): LiveData<List<FavoriteProject>> = favoriteDao.readFavoriteProject()
    fun insertFavoriteProject(favoriteProject: FavoriteProject){
        executorService.execute { favoriteDao.insertFavoriteProject(favoriteProject) }
    }
    fun deleteFavoriteProject(favoriteProject: FavoriteProject){
        executorService.execute { favoriteDao.deleteFavoriteProject(favoriteProject) }
    }

    fun readFavoritePeople() : LiveData<List<FavoritePeople>> = favoriteDao.readFavoritePeople()
    fun searchFavoritePeople(name : String):LiveData<List<FavoritePeople>> = favoriteDao.searchFavoritePeople(name)
    fun insertFavoritePeople(favoritePeople: FavoritePeople){
        executorService.execute { favoriteDao.insertFavoritePeople(favoritePeople) }
    }
    fun deletePersonFavoritePeople(name : String){
        executorService.execute { favoriteDao.deletePersonFavoritePeople(name) }
    }


}