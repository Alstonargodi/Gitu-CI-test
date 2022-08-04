package com.example.favorite.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.data.local.entity.userlist.GithubListUser
import com.example.core.data.local.entity.favoriteproject.FavoriteProject
import com.example.core.data.local.entity.favoriteuser.FavoriteUser
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
            responGithubListUser = favoriteUseCase.readFavoritePeople()
            _isLoading.value = false
        }catch (e : java.lang.Exception){
            _isLoading.value = false
            Log.d(EXTRA_TAG,e.message.toString())
        }
    }

    fun searchFavoritePeople(name : String) : LiveData<List<FavoriteUser>> =
        favoriteUseCase.searchFavoritePeople(name)

    fun insertFavoritePeople(githubListUser: FavoriteUser){
        favoriteUseCase.insertFavoritePeople(githubListUser)
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