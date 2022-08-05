package com.example.githubuser.presentation.fragment.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.data.local.entity.favorite.favoriteuser.FavoriteUser
import com.example.core.data.remote.utils.Resource
import com.example.core.domain.local.FavoriteUseCase
import com.example.core.domain.model.UserDetail
import com.example.core.domain.remote.RemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val remoteUseCase: RemoteUseCase,
    private val favoriteUseCase : FavoriteUseCase
): ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String> = _errorResponse

    fun getUserDetail(name : String): LiveData<Resource<List<UserDetail>>>{
        return remoteUseCase.getUserDetail(name).asLiveData()
    }

    fun searchFavoritePeople(name : String) : LiveData<List<FavoriteUser>> =
        favoriteUseCase.searchFavoritePeople(name)

    fun insertFavoritePeople(data: FavoriteUser){
        favoriteUseCase.insertFavoritePeople(data)
    }

    fun deletePersonFavoritePeople(name: String){
        favoriteUseCase.deletePersonFavoritePeople(name)
    }


}