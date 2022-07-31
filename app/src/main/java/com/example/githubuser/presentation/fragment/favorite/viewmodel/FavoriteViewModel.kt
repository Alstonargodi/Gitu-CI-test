package com.example.githubuser.presentation.fragment.favorite.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.data.local.entity.userlist.GithubUserList
import com.example.core.data.local.entity.githubrepository.GithubRepositoryList
import com.example.core.domain.local.FavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteUseCase : FavoriteUseCase
): ViewModel() {

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _responFavoriteRepo = MutableLiveData<List<GithubRepositoryList>>()
    var responFavoriteRepo : LiveData<List<GithubRepositoryList>> = _responFavoriteRepo

    private var _responGithubUserList = MutableLiveData<List<GithubUserList>>()
    var responGithubUserList : LiveData<List<GithubUserList>> = _responGithubUserList


    fun readFavoritePeople(){
        _isLoading.value = true
        try {
            responGithubUserList = favoriteUseCase.readFavoritePeople()
            _isLoading.value = false
        }catch (e : java.lang.Exception){
            _isLoading.value = false
            Log.d(EXTRA_TAG,e.message.toString())
        }
    }

    fun searchFavoritePeople(name : String) : LiveData<List<GithubUserList>> = favoriteUseCase.searchFavoritePeople(name)
    fun inserFavoritePeople(githubUserList: GithubUserList){
        favoriteUseCase.insertFavoritePeople(githubUserList)
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
    fun insertFavoriteRepo(githubRepositoryList: GithubRepositoryList){
        favoriteUseCase.insertFavoriteProject(githubRepositoryList)
    }
    fun deleteFavoriteRepo(githubRepositoryList: GithubRepositoryList){
        favoriteUseCase.deleteFavoriteProject(githubRepositoryList)
    }


    companion object{
        const val EXTRA_TAG = "favoriteviewmodel"
    }

}