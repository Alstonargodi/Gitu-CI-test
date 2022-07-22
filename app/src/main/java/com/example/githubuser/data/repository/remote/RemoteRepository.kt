package com.example.githubuser.data.repository.remote

import android.util.Log
import com.example.githubuser.data.local.source.LocalDataSource
import com.example.githubuser.data.remote.NetworkBoundResources
import com.example.githubuser.data.remote.apiresponse.ListUserResponse
import com.example.githubuser.data.remote.apiresponse.coderepository.RepositoryUserResponse
import com.example.githubuser.data.remote.apiresponse.detail.DetailUserResponse
import com.example.githubuser.data.remote.apiresponse.follower.FollowerUserResponse
import com.example.githubuser.data.remote.source.IRemoteDataSource
import com.example.githubuser.data.remote.utils.FetchResults
import com.example.githubuser.data.remote.utils.Resource
import com.example.githubuser.domain.model.ListUser
import com.example.githubuser.presentation.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Call

class RemoteRepository(
    private val remoteDataSource: IRemoteDataSource,
    private val localDataSource: LocalDataSource
): IRemoteRepository {

    override fun getListUser(userName: String): Flow<Resource<List<ListUser>>> =
        object : NetworkBoundResources<List<ListUser>, ListUserResponse>(){
            override fun loadFromDB(): Flow<List<ListUser>> {
               return localDataSource.readListUser().map {
                   Log.d("remoteRepository repository",it[0].name)
                   DataMapper.entitesToDomain(it)
               }
            }

            override fun shouldFetch(data: List<ListUser>?): Boolean = true

            override suspend fun createCall(): Flow<FetchResults<ListUserResponse>> =
                remoteDataSource.getListUser(userName)


            override suspend fun saveCallResult(data: ListUserResponse) {
                val userList = DataMapper.remoteResponseToLocalEntites(data)
                localDataSource.insertListUser(userList)
            }

        }.asFlow()


    override fun getUserDetail(name: String): Call<DetailUserResponse> =
        remoteDataSource.getUserDetail(name)

    override fun getUserRepository(name: String): Call<RepositoryUserResponse> =
        remoteDataSource.getUserRepository(name)

    override fun getUserFollowing(name: String): Call<FollowerUserResponse> =
        remoteDataSource.getUserFollowing(name)

    override fun getUserFollower(name: String): Call<FollowerUserResponse> =
        remoteDataSource.getUserFollower(name)


}