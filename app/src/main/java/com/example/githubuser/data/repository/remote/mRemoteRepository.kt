package com.example.githubuser.data.repository.remote

import com.example.githubuser.data.remote.apiresponse.ListUserResponse
import com.example.githubuser.data.remote.apiresponse.coderepository.RepositoryUserResponse
import com.example.githubuser.data.remote.apiresponse.detail.DetailUserResponse
import com.example.githubuser.data.remote.apiresponse.follower.FollowerUserResponse
import com.example.githubuser.data.remote.source.IRemoteDataSource
import com.example.githubuser.data.remote.source.RemoteDataSource
import retrofit2.Call

class mRemoteRepository(
    private val dataSource: IRemoteDataSource
): IRemoteRepository {
    override fun getListUser(userName: String): Call<ListUserResponse> =
        dataSource.getListUser(userName)


    override fun getUserDetail(name: String): Call<DetailUserResponse> =
        dataSource.getUserDetail(name)

    override fun getUserRepository(name: String): Call<RepositoryUserResponse> =
        dataSource.getUserRepository(name)

    override fun getUserFollowing(name: String): Call<FollowerUserResponse> =
        dataSource.getUserFollowing(name)

    override fun getUserFollower(name: String): Call<FollowerUserResponse> =
        dataSource.getUserFollower(name)


}