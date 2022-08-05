package com.example.core.data.repository.remote

import android.util.Log
import com.example.core.data.local.source.githubapi.LocalDataSource
import com.example.core.data.remote.NetworkBoundResources
import com.example.core.data.remote.apiresponse.ListUserResponse
import com.example.core.data.remote.apiresponse.coderepository.RepositoryUserResponse
import com.example.core.data.remote.apiresponse.detail.DetailUserResponse
import com.example.core.data.remote.apiresponse.follower.FollowerUserResponse
import com.example.core.data.remote.source.RemoteDataSource
import com.example.core.data.remote.utils.FetchResults
import com.example.core.data.remote.utils.Resource
import com.example.core.domain.model.*
import com.example.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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


    override fun getUserRepository(name: String): Flow<Resource<List<UserRepository>>> =
        object : NetworkBoundResources<List<UserRepository>,RepositoryUserResponse>(){
            override fun loadFromDB(): Flow<List<UserRepository>> {
               return localDataSource.readUserRepository().map {
                   DataMapper.entitiesUserRepositoryToDomainUserRepository(it)
               }
            }

            override fun shouldFetch(data: List<UserRepository>?): Boolean = true

            override suspend fun createCall(): Flow<FetchResults<RepositoryUserResponse>> {
               return remoteDataSource.getUserRepository(name)
            }

            override suspend fun saveCallResult(data: RepositoryUserResponse) {
                localDataSource.insertUserRepository(
                    DataMapper.remoteUserRepositoryToLocalUserRepository(data)
                )
            }
        }.asFlow()

    override fun getUserFollower(name: String): Flow<Resource<List<UserFollower>>> =
        object : NetworkBoundResources<List<UserFollower>,FollowerUserResponse>() {
            override fun loadFromDB(): Flow<List<UserFollower>> {
                return localDataSource.readUserFollower().map {
                    DataMapper.entitiesUserFollowerToDomainUserFollower(it)
                }
            }
            override fun shouldFetch(data: List<UserFollower>?): Boolean = true
            override suspend fun createCall(): Flow<FetchResults<FollowerUserResponse>> {
               return remoteDataSource.getUserFollower(name)
            }
            override suspend fun saveCallResult(data: FollowerUserResponse) {
                localDataSource.insertUserFollower(
                    DataMapper.remoteUserFollowerToLocalUserFollower(data)
                )
            }
        }.asFlow()

    override fun getUserFollowing(name: String): Flow<Resource<List<UserFollowing>>> =
        object : NetworkBoundResources<List<UserFollowing>,FollowerUserResponse>(){
            override fun loadFromDB(): Flow<List<UserFollowing>> {
                return localDataSource.readUserFollowing().map {
                    DataMapper.entitiesUserFollowingToDomainUserFollowing(it)
                }
            }
            override fun shouldFetch(data: List<UserFollowing>?): Boolean =
                true
            override suspend fun createCall(): Flow<FetchResults<FollowerUserResponse>> {
                return remoteDataSource.getUserFollowing(name)
            }
            override suspend fun saveCallResult(data: FollowerUserResponse) {
                localDataSource.insertUserFollowing(
                    DataMapper.remoteUserFollowingToLocalUserFollowing(data)
                )
            }
        }.asFlow()

    override fun getUserDetail(name: String): Flow<Resource<List<UserDetail>>> =
        object : NetworkBoundResources<List<UserDetail>,DetailUserResponse>(){
            override fun loadFromDB(): Flow<List<UserDetail>> {

                Log.d("remote",localDataSource.readUserDetail().toString())

                return localDataSource.readUserDetail().map {

                    DataMapper.entitiesUserDetailToDomainUserDetail(it)
                }
            }
            override fun shouldFetch(data: List<UserDetail>?): Boolean = true

            override suspend fun createCall(): Flow<FetchResults<DetailUserResponse>> {
                return remoteDataSource.getUserDetail(name)
            }
            override suspend fun saveCallResult(data: DetailUserResponse) {
                Log.d("remote","${data.name}")
                localDataSource.insetUserDetail(
                    DataMapper.remoteUserDetailToLocalUserDetail(data)
                )
            }
        }.asFlow()


    override fun showHistoryListUser(): Flow<List<ListUser>> {
        return localDataSource.readListUser().map {
            DataMapper.entitiesUserListToDomainUserList(it)
        }
    }

    override fun deleteListUser() {
        localDataSource.deleteListUser()
    }

    override fun deleteUseRepository() {
        localDataSource.deleteUseRepository()
    }

    override fun deleteUserFollower() {
        localDataSource.deleteUserFollower()
    }

    override fun deleteUserFollowing() {
        localDataSource.deleteUserFollowing()
    }

    override fun deleteUserDetail() {
        localDataSource.deleteUserDetail()
    }


}