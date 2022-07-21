package com.example.githubuser.data.repository.remote

import com.example.githubuser.data.local.entity.userlist.GithubListUser
import com.example.githubuser.data.local.source.LocalDataSource
import com.example.githubuser.data.remote.NetworkBoundResources
import com.example.githubuser.data.remote.apiresponse.ListUserResponse
import com.example.githubuser.data.remote.apiresponse.coderepository.RepositoryUserResponse
import com.example.githubuser.data.remote.apiresponse.detail.DetailUserResponse
import com.example.githubuser.data.remote.apiresponse.follower.FollowerUserResponse
import com.example.githubuser.data.remote.source.IRemoteDataSource
import com.example.githubuser.data.remote.source.RemoteDataSource
import com.example.githubuser.data.remote.utils.FetchResults
import com.example.githubuser.data.remote.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Call

class RemoteRepository(
    private val dataSource: IRemoteDataSource,
    private val localDataSource: LocalDataSource
): IRemoteRepository {

    override fun getListUser(userName: String): Flow<Resource<List<GithubListUser>>> =
        object : NetworkBoundResources<List<GithubListUser>, List<ListUserResponse>>(){
            override fun loadFromDB(): Flow<List<GithubListUser>> {
                TODO("Not yet implemented")
            }

            override fun shouldFetch(data: List<GithubListUser>?): Boolean {
                TODO("Not yet implemented")
            }

            override suspend fun createCall(): Flow<FetchResults<List<ListUserResponse>>> {
                TODO("Not yet implemented")
            }

            override suspend fun saveCallResult(data: List<ListUserResponse>) {
                TODO("Not yet implemented")
            }

        }.asFlow()


    override fun getUserDetail(name: String): Call<DetailUserResponse> =
        dataSource.getUserDetail(name)

    override fun getUserRepository(name: String): Call<RepositoryUserResponse> =
        dataSource.getUserRepository(name)

    override fun getUserFollowing(name: String): Call<FollowerUserResponse> =
        dataSource.getUserFollowing(name)

    override fun getUserFollower(name: String): Call<FollowerUserResponse> =
        dataSource.getUserFollower(name)


}