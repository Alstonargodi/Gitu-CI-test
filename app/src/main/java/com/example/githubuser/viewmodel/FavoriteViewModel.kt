package com.example.githubuser.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.local.FavoriteRepository
import com.example.githubuser.local.entity.FavoritePeople
import com.example.githubuser.local.entity.FavoriteProject

class FavoriteViewModel(application: Application): ViewModel() {
    private val mNoteRepo : FavoriteRepository = FavoriteRepository(application)

    fun readFavoritePeople() : LiveData<List<FavoritePeople>> = mNoteRepo.readFavoritePeople()

    fun inserFavoritePeople(favoritePeople: FavoritePeople){
        mNoteRepo.insertFavoritePeople(favoritePeople)
    }
    fun updateFavoritePeople(favoritePeople: FavoritePeople){
        mNoteRepo.updateFavoritePeople(favoritePeople)
    }
    fun deleteFavoritePeople(favoritePeople: FavoritePeople){
        mNoteRepo.deleteFavoritePeople(favoritePeople)
    }


    fun insertFavoriteRepo(favoriteProject: FavoriteProject){
        mNoteRepo.insertFavoriteProject(favoriteProject)
    }

    fun updateFavoriteRepo(favoriteProject: FavoriteProject){
        mNoteRepo.updateFavoriteProject(favoriteProject)
    }

    fun deleteFavoriteRepo(favoriteProject: FavoriteProject){
        mNoteRepo.deleteFavoriteProject(favoriteProject)
    }


}