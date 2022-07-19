package com.example.githubuser.domain.local

import androidx.lifecycle.LiveData
import com.example.githubuser.data.local.entity.favoritepeople.FavoritePeople
import com.example.githubuser.data.local.entity.favoriteproject.FavoriteProject
import com.example.githubuser.data.repository.favorite.IFavoriteRepository

class FavoriteInteractor(private val repository: IFavoriteRepository): FavoriteUseCase {
    override fun insertFavoriteProject(favoriteProject: FavoriteProject) {
        repository.insertFavoriteProject(favoriteProject)
    }

    override fun insertFavoritePeople(favoritePeople: FavoritePeople) {
       repository.insertFavoritePeople(favoritePeople)
    }

    override fun readFavoriteProject(): LiveData<List<FavoriteProject>> =
        repository.readFavoriteProject()


    override fun readFavoritePeople(): LiveData<List<FavoritePeople>> =
        repository.readFavoritePeople()

    override fun searchFavoritePeople(name: String): LiveData<List<FavoritePeople>> =
        repository.searchFavoritePeople(name)

    override fun deleteFavoriteProject(favoriteProject: FavoriteProject) =
        repository.deleteFavoriteProject(favoriteProject)

    override fun deletePersonFavoritePeople(name: String) =
        repository.deletePersonFavoritePeople(name)

}