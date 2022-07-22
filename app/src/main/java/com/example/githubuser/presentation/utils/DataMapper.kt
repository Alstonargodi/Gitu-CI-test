package com.example.githubuser.presentation.utils

import android.util.Log
import com.example.githubuser.data.local.entity.userlist.GithubListUser
import com.example.githubuser.data.remote.apiresponse.ListUserResponse
import com.example.githubuser.domain.model.ListUser

object DataMapper {

    fun remoteResponseToLocalEntites(data : ListUserResponse): List<GithubListUser>{
        val dataList = ArrayList<GithubListUser>()
        data.items.map { response ->
            val listUser = GithubListUser(
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

    fun entitesToDomain(input : List<GithubListUser>): List<ListUser> =
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

    fun domainToEntity(data : ListUser) = GithubListUser(
        name = data.name,
        username = data.username,
        imageLink = data.imageLink,
        location = data.location,
        company = data.company,
        isSaved = data.isSaved
    )


}