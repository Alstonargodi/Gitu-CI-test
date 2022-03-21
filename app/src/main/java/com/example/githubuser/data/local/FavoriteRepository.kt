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
    fun readFavoritePeople() : LiveData<List<FavoritePeople>> = mFavDao.readFavoritePeople()

    fun insertFavoriteProject(favoriteProject: FavoriteProject){
        executorService.execute { mFavDao.insertFavoriteProject(favoriteProject) }
    }
    fun deleteFavoriteProject(favoriteProject: FavoriteProject){
        executorService.execute { mFavDao.deleteFavoriteProject(favoriteProject) }
    }



    fun insertFavoritePeople(favoritePeople: FavoritePeople){
        executorService.execute { mFavDao.insertFavoritePeople(favoritePeople) }
    }
    fun deleteFavoritePeople(favoritePeople: FavoritePeople){
        executorService.execute { mFavDao.deleteFavoritePeople(favoritePeople) }
    }


}