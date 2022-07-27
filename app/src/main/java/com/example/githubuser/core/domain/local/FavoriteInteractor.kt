package com.example.githubuser.core.domain.local

import androidx.lifecycle.LiveData
import com.example.githubuser.core.data.local.entity.userlist.GithubListUser
import com.example.githubuser.core.data.local.entity.favoriteproject.FavoriteProject
import com.example.githubuser.core.data.repository.favorite.IFavoriteRepository
import javax.inject.Inject

class FavoriteInteractor @Inject constructor(
    private val repository: IFavoriteRepository
): FavoriteUseCase {

    override fun insertFavoriteProject(favoriteProject: FavoriteProject) =
        repository.insertFavoriteProject(favoriteProject)


    override fun insertFavoritePeople(githubListUser: GithubListUser) =
       repository.insertFavoritePeople(githubListUser)


    override fun readFavoriteProject(): LiveData<List<FavoriteProject>> =
        repository.readFavoriteProject()


    override fun readFavoritePeople(): LiveData<List<GithubListUser>> =
        repository.readFavoritePeople()

    override fun searchFavoritePeople(name: String): LiveData<List<GithubListUser>> =
        repository.searchFavoritePeople(name)

    override fun deleteFavoriteProject(favoriteProject: FavoriteProject) =
        repository.deleteFavoriteProject(favoriteProject)

    override fun deletePersonFavoritePeople(name: String) =
        repository.deletePersonFavoritePeople(name)

}