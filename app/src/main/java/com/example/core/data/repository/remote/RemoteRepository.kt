package com.example.core.data.repository.remote

import com.example.core.data.local.source.githubapi.LocalDataSource
import com.example.core.data.remote.NetworkBoundResources
import com.example.core.data.remote.apiresponse.ListUserResponse
import com.example.core.data.remote.apiresponse.coderepository.RepositoryUserResponse
import com.example.core.data.remote.apiresponse.detail.DetailUserResponse
import com.example.core.data.remote.apiresponse.follower.FollowerUserResponse
import com.example.core.data.remote.source.RemoteDataSource
import com.example.core.data.remote.utils.FetchResults
import com.example.core.data.remote.utils.Resource
import com.example.core.domain.model.ListUser
import com.example.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): IRemoteRepository {

    override fun getListUser(userName: String): Flow<Resource<List<ListUser>>> =
        object : NetworkBoundResources<List<ListUser>, ListUserResponse>(){
            override fun loadFromDB(): Flow<List<ListUser>> {
               return localDataSource.readListUser().map {
                   DataMapper.entitiesUserListToDomainUserList(it)
               }
            }
            override fun shouldFetch(data: List<ListUser>?): Boolean = true
            override suspend fun createCall(): Flow<FetchResults<ListUserResponse>> =
                remoteDataSource.getListUser(userName)
            override suspend fun saveCallResult(data: ListUserResponse) {
                val userList = DataMapper.remoteUserListToLocalUserList(data)
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