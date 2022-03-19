package com.example.githubuser.local

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.githubuser.local.entity.FavoritePeople
import com.example.githubuser.local.entity.FavoriteProject
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
    fun readFavoritePeople() : LiveData<List<FavoritePeople>> = mFavDao.readFavoritePeople()

    fun insertFavoriteProject(favoriteProject: FavoriteProject){
        executorService.execute { mFavDao.insertFavoriteProject(favoriteProject) }
    }
    fun updateFavoriteProject(favoriteProject: FavoriteProject){
        executorService.execute { mFavDao.updateFavoriteProject(favoriteProject) }
    }
    fun deleteFavoriteProject(favoriteProject: FavoriteProject){
        executorService.execute { mFavDao.deleteFavoriteProject(favoriteProject) }
    }



    fun insertFavoritePeople(favoritePeople: FavoritePeople){
        executorService.execute { mFavDao.insertFavoritePeople(favoritePeople) }
    }
    fun updateFavoritePeople(favoritePeople: FavoritePeople){
        executorService.execute { mFavDao.updateFavoritePeople(favoritePeople) }
    }
    fun deleteFavoritePeople(favoritePeople: FavoritePeople){
        executorService.execute { mFavDao.deleteFavoritePeople(favoritePeople) }
    }


}