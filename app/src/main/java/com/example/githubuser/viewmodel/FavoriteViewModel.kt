package com.example.githubuser.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.local.FavoriteRepository
import com.example.githubuser.local.entity.FavoritePeople
import com.example.githubuser.local.entity.FavoriteProject

class FavoriteViewModel(application: Application): ViewModel() {
    private val mFavRepo : FavoriteRepository = FavoriteRepository(application)

    fun readFavoritePeople() : LiveData<List<FavoritePeople>> = mFavRepo.readFavoritePeople()
    fun readFavoriteProject() :  LiveData<List<FavoriteProject>> = mFavRepo.readFavoriteProject()
    fun inserFavoritePeople(favoritePeople: FavoritePeople){
        mFavRepo.insertFavoritePeople(favoritePeople)
    }
    fun deleteFavoritePeople(favoritePeople: FavoritePeople){
        mFavRepo.deleteFavoritePeople(favoritePeople)
    }


    fun insertFavoriteRepo(favoriteProject: FavoriteProject){
        mFavRepo.insertFavoriteProject(favoriteProject)
    }
    fun deleteFavoriteRepo(favoriteProject: FavoriteProject){
        mFavRepo.deleteFavoriteProject(favoriteProject)
    }


}