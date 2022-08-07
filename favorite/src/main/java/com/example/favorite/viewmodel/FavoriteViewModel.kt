package com.example.favorite.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.data.local.entity.favorite.favoriteproject.FavoriteProject
import com.example.core.data.local.entity.favorite.favoriteuser.FavoriteUser
import com.example.core.domain.local.FavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteUseCase : FavoriteUseCase
): ViewModel() {

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _responFavoriteRepo = MutableLiveData<List<FavoriteProject>>()
    var responFavoriteRepo : LiveData<List<FavoriteProject>> = _responFavoriteRepo

    private var _responGithubListUser = MutableLiveData<List<FavoriteUser>>()
    var responGithubListUser : LiveData<List<FavoriteUser>> = _responGithubListUser


    fun readFavoritePeople(){
        _isLoading.value = true
        try {
            responGithubListUser = favoriteUseCase.readFavoritePeople().asLiveData()
            _isLoading.value = false
        }catch (e : java.lang.Exception){
            _isLoading.value = false
            Log.d(EXTRA_TAG,e.message.toString())
        }
    }


    fun deletePersonFavoritePeople(name: String){
        favoriteUseCase.deletePersonFavoritePeople(name)
    }


    fun readFavoriteProject(){
        _isLoading.value = true
        try {
            responFavoriteRepo = favoriteUseCase.readFavoriteProject().asLiveData()
            _isLoading.value = false
        }catch (e : java.lang.Exception){
            _isLoading.value = false
            Log.d(EXTRA_TAG,e.message.toString())
        }
    }

    fun deleteFavoriteRepo(favoriteProject: FavoriteProject){
        favoriteUseCase.deleteFavoriteProject(favoriteProject)
    }


    companion object{
        const val EXTRA_TAG = "favoriteviewmodel"
    }

}