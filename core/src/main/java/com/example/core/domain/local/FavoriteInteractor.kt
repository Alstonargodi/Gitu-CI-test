package com.example.core.domain.local

import androidx.lifecycle.LiveData
import com.example.core.data.local.entity.favorite.favoriteproject.FavoriteProject
import com.example.core.data.local.entity.favorite.favoriteuser.FavoriteUser
import com.example.core.data.repository.favorite.IFavoriteRepository
import javax.inject.Inject

class FavoriteInteractor @Inject constructor(
    private val repository: IFavoriteRepository
): FavoriteUseCase {
    override fun insertFavoriteProject(favoriteProject: FavoriteProject) =
        repository.insertFavoriteProject(favoriteProject)
    override fun insertFavoritePeople(githubListUser: FavoriteUser) =
       repository.insertFavoritePeople(githubListUser)
    override fun readFavoriteProject(): LiveData<List<FavoriteProject>> =
        repository.readFavoriteProject()
    override fun readFavoritePeople(): LiveData<List<FavoriteUser>> =
        repository.readFavoritePeople()
    override fun searchFavoritePeople(name: String): LiveData<List<FavoriteUser>> =
        repository.searchFavoritePeople(name)
    override fun deleteFavoriteProject(favoriteProject: FavoriteProject) =
        repository.deleteFavoriteProject(favoriteProject)
    override fun deletePersonFavoritePeople(name: String) =
        repository.deletePersonFavoritePeople(name)
}