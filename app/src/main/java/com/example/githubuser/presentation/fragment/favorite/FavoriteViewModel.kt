package com.example.githubuser.presentation.fragment.favorite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.data.local.entity.favoritepeople.FavoritePeople
import com.example.githubuser.data.local.entity.favoriteproject.FavoriteProject
import com.example.githubuser.domain.local.FavoriteUseCase

class FavoriteViewModel(
    private val favoriteUseCase : FavoriteUseCase
): ViewModel() {

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _responFavoriteRepo = MutableLiveData<List<FavoriteProject>>()
    var responFavoriteRepo : LiveData<List<FavoriteProject>> = _responFavoriteRepo

    private var _responFavoritePeople = MutableLiveData<List<FavoritePeople>>()
    var responFavoritePeople : LiveData<List<FavoritePeople>> = _responFavoritePeople


    fun readFavoritePeople(){
        _isLoading.value = true
        try {
            responFavoritePeople = favoriteUseCase.readFavoritePeople()
            _isLoading.value = false
        }catch (e : java.lang.Exception){
            _isLoading.value = false
            Log.d(EXTRA_TAG,e.message.toString())
        }
    }

    fun searchFavoritePeople(name : String) : LiveData<List<FavoritePeople>> = favoriteUseCase.searchFavoritePeople(name)
    fun inserFavoritePeople(favoritePeople: FavoritePeople){
        favoriteUseCase.insertFavoritePeople(favoritePeople)
    }
    fun deletePersonFavoritePeople(name: String){
        favoriteUseCase.deletePersonFavoritePeople(name)

    }


    fun readFavoriteProject(){
        _isLoading.value = true
        try {
            responFavoriteRepo = favoriteUseCase.readFavoriteProject()
            _isLoading.value = false
        }catch (e : java.lang.Exception){
            _isLoading.value = false
            Log.d(EXTRA_TAG,e.message.toString())
        }
    }
    fun insertFavoriteRepo(favoriteProject: FavoriteProject){
        favoriteUseCase.insertFavoriteProject(favoriteProject)
    }
    fun deleteFavoriteRepo(favoriteProject: FavoriteProject){
        favoriteUseCase.deleteFavoriteProject(favoriteProject)
    }


    companion object{
        const val EXTRA_TAG = "favoriteviewmodel"
    }

}