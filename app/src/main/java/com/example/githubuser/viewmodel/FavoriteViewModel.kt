package com.example.githubuser.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.data.local.FavoriteRepository
import com.example.githubuser.data.local.entity.FavoritePeople
import com.example.githubuser.data.local.entity.FavoriteProject

class FavoriteViewModel(application: Application): ViewModel() {
    private val mFavRepo : FavoriteRepository = FavoriteRepository(application)

    private var _responseStatus = MutableLiveData<String>()
    val responseStatus: LiveData<String> = _responseStatus

    private var _responFavoriteRepo = MutableLiveData<List<FavoriteProject>>()
    var responFavoriteRepo : LiveData<List<FavoriteProject>> = _responFavoriteRepo

    fun readFavoritePeople() : LiveData<List<FavoritePeople>> = mFavRepo.readFavoritePeople()

    fun readFavoriteProject(){
        try {
            val respon = mFavRepo.readFavoriteProject()
            if (respon.value?.isNullOrEmpty() == true){
                _responseStatus.value = EXTRA_MESSAGE
            }else{
                _responseStatus.value = respon.value.toString()
                responFavoriteRepo = mFavRepo.readFavoriteProject()
            }
        }catch (e : java.lang.Exception){
            _responseStatus.value = e.message.toString()
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
        const val EXTRA_MESSAGE =  "No Favorite People"
    }

}