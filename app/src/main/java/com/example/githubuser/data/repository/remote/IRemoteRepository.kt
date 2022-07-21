package com.example.githubuser.data.repository.remote

import com.example.githubuser.data.local.entity.userlist.GithubListUser
import com.example.githubuser.data.remote.apiresponse.ListUserResponse
import com.example.githubuser.data.remote.apiresponse.coderepository.RepositoryUserResponse
import com.example.githubuser.data.remote.apiresponse.detail.DetailUserResponse
import com.example.githubuser.data.remote.apiresponse.follower.FollowerUserResponse
import com.example.githubuser.data.remote.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Call

interface IRemoteRepository {
    fun getListUser(userName : String): Flow<Resource<List<GithubListUser>>>
    fun getUserDetail(name: String): Call<DetailUserResponse>
    fun getUserRepository(name : String): Call<RepositoryUserResponse>
    fun getUserFollowing(name: String): Call<FollowerUserResponse>
    fun getUserFollower(name: String): Call<FollowerUserResponse>
}