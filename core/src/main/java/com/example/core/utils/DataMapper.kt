package com.example.core.utils

import android.util.Log
import com.example.core.data.local.entity.favorite.favoriteuser.FavoriteUser
import com.example.core.data.local.entity.remote.following.GithubUserFollower
import com.example.core.data.local.entity.userlist.GithubListUser
import com.example.core.data.local.entity.remote.userproject.GithubUserProject
import com.example.core.data.remote.apiresponse.ListUserResponse
import com.example.core.data.remote.apiresponse.coderepository.RepositoryUserResponse
import com.example.core.data.remote.apiresponse.detail.DetailUserResponse
import com.example.core.data.remote.apiresponse.follower.FollowerUserResponse
import com.example.core.domain.model.ListUser
import com.example.core.domain.model.UserFollower
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
            repositoryList.add(GithubUserProject(
                id=it.id,
                name = it.name,
                description = it.description ?: "",
                forksCount = it.forksCount,
                language = it.language ?: "",
                stargazersCount = it.stargazersCount,
            ))
        }
        return repositoryList
    }

    fun entitiesUserRepositoryToDomainUserRepository(input : List<GithubUserProject>): List<UserRepository> =
        input.map {
            UserRepository(
                name = it.name,
                description = it.description ?: "",
                forksCount = it.forksCount,
                language = it.language ?: "",
                stargazersCount = it.stargazersCount,
            )
        }

    fun remoteUserFollowerToLocalUserFollower(data : FollowerUserResponse): List<GithubUserFollower>{
        val followerList = ArrayList<GithubUserFollower>()
        data.forEach {
            followerList.add(
                    GithubUserFollower(
                    id = it.id,
                    username = it.login,
                    avatarUrl = it.avatarUrl
                )
            )
        }
        return followerList
    }

    fun entitiesUserFollowerToDomainUserFollower(data : List<GithubUserFollower>): List<UserFollower> =
        data.map {
            UserFollower(
                id = it.id,
                username = it.username,
                avatarUrl = it.avatarUrl
            )
        }


}