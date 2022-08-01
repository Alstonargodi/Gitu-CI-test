package com.example.core.domain.local

import androidx.lifecycle.LiveData
import com.example.core.data.local.entity.userlist.GithubUserList
import com.example.core.data.local.entity.githubrepository.GithubRepositoryList
import com.example.core.data.repository.favorite.IFavoriteRepository
import javax.inject.Inject

class FavoriteInteractor @Inject constructor(
    private val repository: IFavoriteRepository
): FavoriteUseCase {
    override fun insertFavoriteProject(githubRepositoryList: GithubRepositoryList) =
        repository.insertFavoriteProject(githubRepositoryList)
    override fun insertFavoritePeople(githubUserList: GithubUserList) =
       repository.insertFavoritePeople(githubUserList)
    override fun readFavoriteProject(): LiveData<List<GithubRepositoryList>> =
        repository.readFavoriteProject()
    override fun readFavoritePeople(): LiveData<List<GithubUserList>> =
        repository.readFavoritePeople()
    override fun searchFavoritePeople(name: String): LiveData<List<GithubUserList>> =
        repository.searchFavoritePeople(name)
    override fun deleteFavoriteProject(githubRepositoryList: GithubRepositoryList) =
        repository.deleteFavoriteProject(githubRepositoryList)
    override fun deletePersonFavoritePeople(name: String) =
        repository.deletePersonFavoritePeople(name)

}