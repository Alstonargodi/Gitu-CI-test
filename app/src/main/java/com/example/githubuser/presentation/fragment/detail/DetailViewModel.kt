package com.example.githubuser.presentation.fragment.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.data.local.entity.favorite.favoriteuser.FavoriteUser
import com.example.core.data.remote.apiresponse.detail.DetailUserResponse
import com.example.core.domain.local.FavoriteUseCase
import com.example.core.domain.remote.RemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val remote: RemoteUseCase,
    private val favorite : FavoriteUseCase
): ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String> = _errorResponse

    fun getUserDetail(name : String): LiveData<DetailUserResponse>{
        return remote.getUserDetail(name)
    }

    fun searchFavoritePeople(name : String) : LiveData<List<FavoriteUser>> =
        favorite.searchFavoritePeople(name)

    fun insertFavoritePeople(data: FavoriteUser){
        favorite.insertFavoritePeople(data)
    }

    fun deletePersonFavoritePeople(name: String){
        favorite.deletePersonFavoritePeople(name)
    }


}