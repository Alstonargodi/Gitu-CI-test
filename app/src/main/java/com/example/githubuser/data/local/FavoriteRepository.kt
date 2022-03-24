package com.example.githubuser.data.local

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.githubuser.data.local.entity.FavoritePeople
import com.example.githubuser.data.local.entity.FavoriteProject
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(application: Application) {
    private val mFavDao : FavoriteDao
    private val executorService : ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteDatabase.setDatabase(application)
        mFavDao = db.favoritedao()
    }

    fun readFavoriteProject(): LiveData<List<FavoriteProject>> = mFavDao.readFavoriteProject()
    fun insertFavoriteProject(favoriteProject: FavoriteProject){
        executorService.execute { mFavDao.insertFavoriteProject(favoriteProject) }
    }
    fun deleteFavoriteProject(favoriteProject: FavoriteProject){
        executorService.execute { mFavDao.deleteFavoriteProject(favoriteProject) }
    }


    fun readFavoritePeople() : LiveData<List<FavoritePeople>> = mFavDao.readFavoritePeople()
    fun searchFavoritePeople(name : String):LiveData<List<FavoritePeople>> = mFavDao.searchFavoritePeople(name)
    fun insertFavoritePeople(favoritePeople: FavoritePeople){
        executorService.execute { mFavDao.insertFavoritePeople(favoritePeople) }
    }
    fun deletePersonFavoritePeople(name : String){
        executorService.execute { mFavDao.deletePersonFavoritePeople(name) }
    }


}