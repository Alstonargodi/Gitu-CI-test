package com.example.core.domain.local

import androidx.lifecycle.LiveData
import com.example.core.data.local.entity.favorite.favoriteproject.FavoriteProject
import com.example.core.data.local.entity.favorite.favoriteuser.FavoriteUser
import com.example.core.data.repository.favorite.IFavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteInteractor @Inject constructor(
    private val repository: IFavoriteRepository
): FavoriteUseCase {
    override fun insertFavoriteProject(favoriteProject: FavoriteProject) =
        repository.insertFavoriteProject(favoriteProject)
    override fun insertFavoritePeople(githubListUser: FavoriteUser) =
       repository.insertFavoritePeople(githubListUser)
    override fun readFavoriteProject(): Flow<List<FavoriteProject>> =
        repository.readFavoriteProject()
    override fun readFavoritePeople(): Flow<List<FavoriteUser>> =
        repository.readFavoritePeople()
    override fun searchFavoritePeople(name: String): Flow<List<FavoriteUser>> =
        repository.searchFavoritePeople(name)
    override fun deleteFavoriteProject(favoriteProject: FavoriteProject) =
        repository.deleteFavoriteProject(favoriteProject)
    override fun deletePersonFavoritePeople(name: String) =
        repository.deletePersonFavoritePeople(name)

    override fun deleteUseRepository() {
        repository.deleteUseRepository()
    }

    override fun deleteUserFollower() {
        repository.deleteUserFollower()
    }

    override fun deleteUserFollowing() {
        repository.deleteUserFollowing()
    }

    override fun deleteUserDetail() {
        repository.deleteUserDetail()
    }
}