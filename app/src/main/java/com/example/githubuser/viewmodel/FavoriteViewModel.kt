package com.example.githubuser.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.data.local.FavoriteRepository
import com.example.githubuser.data.local.entity.FavoritePeople
import com.example.githubuser.data.local.entity.FavoriteProject

class FavoriteViewModel(application: Application): ViewModel() {
    private val mFavRepo : FavoriteRepository = FavoriteRepository(application)

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _responFavoriteRepo = MutableLiveData<List<FavoriteProject>>()
    var responFavoriteRepo : LiveData<List<FavoriteProject>> = _responFavoriteRepo

    fun readFavoritePeople() : LiveData<List<FavoritePeople>> = mFavRepo.readFavoritePeople()

    fun readFavoriteProject(){
        _isLoading.value = true
        try {
            responFavoriteRepo = mFavRepo.readFavoriteProject()
            _isLoading.value = false
        }catch (e : java.lang.Exception){
            _isLoading.value = false
            Log.d(EXTRA_TAG,e.message.toString())
        }
    }


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


    companion object{
        const val EXTRA_TAG = "favoriteviewmodel"
    }

}