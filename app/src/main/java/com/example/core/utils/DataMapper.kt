package com.example.core.utils

import com.example.core.data.local.entity.favoriteuser.FavoriteUser
import com.example.core.data.local.entity.userlist.GithubListUser
import com.example.core.data.remote.apiresponse.ListUserResponse
import com.example.core.data.remote.apiresponse.detail.DetailUserResponse
import com.example.core.domain.model.ListUser

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
}