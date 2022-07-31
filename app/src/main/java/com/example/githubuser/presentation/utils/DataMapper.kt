package com.example.githubuser.presentation.utils

import com.example.core.data.local.entity.userlist.GithubUserList
import com.example.core.data.remote.apiresponse.ListUserResponse
import com.example.core.domain.model.ListUser

object DataMapper {

    fun remoteResponseToLocalEntites(data : ListUserResponse): List<GithubUserList>{
        val dataList = ArrayList<GithubUserList>()
        data.items.map { response ->
            val listUser = GithubUserList(
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

    fun entitesToDomain(input : List<GithubUserList>): List<ListUser> =
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

    fun domainToEntity(data : ListUser) = GithubUserList(
        id = 0,
        name = data.name,
        username = data.username,
        imageLink = data.imageLink,
        location = data.location,
        company = data.company,
        isSaved = data.isSaved
    )


}