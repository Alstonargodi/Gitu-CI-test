package com.example.githubuser.presentation.fragment.favorite

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.data.repository.FavoriteRepository
import com.example.githubuser.data.local.entity.favoritepeople.FavoritePeople
import com.example.githubuser.data.local.entity.favoriteproject.FavoriteProject

class FavoriteViewModel(
    favoriteRepository: FavoriteRepository
): ViewModel() {
    private val mFavRepo = favoriteRepository

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _responFavoriteRepo = MutableLiveData<List<FavoriteProject>>()
    var responFavoriteRepo : LiveData<List<FavoriteProject>> = _responFavoriteRepo

    private var _responFavoritePeople = MutableLiveData<List<FavoritePeople>>()
    var responFavoritePeople : LiveData<List<FavoritePeople>> = _responFavoritePeople


    fun readFavoritePeople(){
        _isLoading.value = true
        try {
            responFavoritePeople = mFavRepo.readFavoritePeople()
            _isLoading.value = false
        }catch (e : java.lang.Exception){
            _isLoading.value = false
            Log.d(EXTRA_TAG,e.message.toString())
        }
    }

    fun searchFavoritePeople(name : String) : LiveData<List<FavoritePeople>> = mFavRepo.searchFavoritePeople(name)
    fun inserFavoritePeople(favoritePeople: FavoritePeople){
        mFavRepo.insertFavoritePeople(favoritePeople)
    }
    fun deletePersonFavoritePeople(name: String){
        mFavRepo.deletePersonFavoritePeople(name)

    }


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