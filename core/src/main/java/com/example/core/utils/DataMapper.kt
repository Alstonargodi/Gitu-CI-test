package com.example.core.utils

import android.util.Log
import com.example.core.data.local.entity.favorite.favoriteuser.FavoriteUser
import com.example.core.data.local.entity.userlist.GithubListUser
import com.example.core.data.local.entity.userproject.GithubUserProject
import com.example.core.data.remote.apiresponse.ListUserResponse
import com.example.core.data.remote.apiresponse.coderepository.RepositoryUserResponse
import com.example.core.data.remote.apiresponse.detail.DetailUserResponse
import com.example.core.domain.model.ListUser
import com.example.core.domain.model.UserRepository

object DataMapper {

    fun remoteUserListToLocalUserList(data : ListUserResponse): List<GithubListUser>{
        val dataList = ArrayList<GithubListUser>()
        data.items.map { response ->
            val listUser = GithubListUser(
                id = response.id.toInt(),
                name = response.login,
                username = null,
                imageLink = response.avatarUrl,
                location = null,
                company = null,
                isSaved = false
            )
            dataList.add(listUser)
        }
        return dataList
    }

    fun entitiesUserListToDomainUserList(input : List<GithubListUser>): List<ListUser> =
        input.map { data ->
            ListUser(
                name = data.name,
                username = data.username,
                imageLink = data.imageLink,
                location = data.location,
                company = data.company,
                isSaved = data.isSaved
            )
        }

    fun userSetFavoriteUser(data : DetailUserResponse): FavoriteUser =
        FavoriteUser(
            data.id!!.toInt(),
            data.login.toString(),
            data.name,
            data.avatarUrl,
            data.location,
            data.company,
        )

    fun remoteUserRepositoryToLocalUserRepository(data : RepositoryUserResponse): List<GithubUserProject>{
        val repositoryList = ArrayList<GithubUserProject>()

        data.forEach {
            val temp = GithubUserProject(
                id=it.id,
                name = it.name,
                description = it.description ?: "",
                forksCount = it.forksCount,
                language = it.language ?: "",
                stargazersCount = it.stargazersCount,
            )
            repositoryList.add(temp)
        }
        return repositoryList
    }

    fun entitesUserRepositoryToDomainUserRepository(input : List<GithubUserProject>): List<UserRepository> =
        input.map {
            Log.d("remote",it.toString())
            UserRepository(
                name = it.name,
                description = it.description ?: "",
                forksCount = it.forksCount,
                language = it.language ?: "",
                stargazersCount = it.stargazersCount,
            )
        }



}