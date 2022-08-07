package com.example.core.utils

import com.example.core.data.local.entity.favorite.favoriteuser.FavoriteUser
import com.example.core.data.local.entity.remote.userdetail.GithubUserDetail
import com.example.core.data.local.entity.remote.userfollower.GithubUserFollower
import com.example.core.data.local.entity.remote.userfollowing.GithubUserFollowing
import com.example.core.data.local.entity.userlist.GithubListUser
import com.example.core.data.local.entity.remote.userproject.GithubUserProject
import com.example.core.data.remote.apiresponse.ListUserResponse
import com.example.core.data.remote.apiresponse.coderepository.RepositoryUserResponse
import com.example.core.data.remote.apiresponse.detail.DetailUserResponse
import com.example.core.data.remote.apiresponse.follower.FollowerUserResponse
import com.example.core.domain.model.*

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

    fun userSetFavoriteUser(data : UserDetail): FavoriteUser =
        FavoriteUser(
            data.nickName.toString(),
            data.userName,
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

    fun remoteUserFollowingToLocalUserFollowing(input : FollowerUserResponse): List<GithubUserFollowing>{
        val followingList = ArrayList<GithubUserFollowing>()
        input.map {
            followingList.add(GithubUserFollowing(
                    id = it.id,
                    username = it.login,
                    avatarUrl = it.avatarUrl
                )
            )
        }
        return followingList
    }

    fun entitiesUserFollowingToDomainUserFollowing(data : List<GithubUserFollowing>): List<UserFollowing> =
        data.map {
            UserFollowing(
                id = it.id,
                username = it.username,
                avatarUrl = it.avatarUrl
            )
        }

    fun remoteUserDetailToLocalUserDetail(input : DetailUserResponse): List<GithubUserDetail> {
        val detail = ArrayList<GithubUserDetail>()
        detail.add(GithubUserDetail(
            id = 0,
            userName = input.login ?: "",
            nickName = input.name ?: "",
            bio = input.bio ?: "",
            type = input.type ?: "",
            blog = input.blog ?: "",
            company = input.company ?: "",
            publicRepos = input.publicRepos ?: 0,
            email = input.email ?: "",
            followers = input.followers ?: 0,
            avatarUrl = input.avatarUrl ?: "",
            following = input.following ?: 0,
            location = input.location ?: ""
        ))
        return detail
    }



    fun entitiesUserDetailToDomainUserDetail(input : List<GithubUserDetail>): List<UserDetail> =
        input.map {
            UserDetail(
                id = it.id,
                userName = it.userName,
                nickName = it.nickName,
                bio = it.bio,
                type = it.type ,
                blog = it.blog,
                company = it.company,
                publicRepos = it.publicRepos,
                email = it.email,
                followers = it.followers,
                avatarUrl = it.avatarUrl,
                following = it.following,
                location = it.location
            )
        }


}