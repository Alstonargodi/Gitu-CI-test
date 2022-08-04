package com.example.core.data.remote.source

import android.util.Log
import com.example.core.data.remote.apiresponse.ListUserResponse
import com.example.core.data.remote.apiresponse.coderepository.RepositoryUserResponse
import com.example.core.data.remote.apiresponse.detail.DetailUserResponse
import com.example.core.data.remote.apiresponse.follower.FollowerUserResponse
import com.example.core.data.remote.apiservice.ApiService
import com.example.core.data.remote.utils.FetchResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService : ApiService
): IRemoteDataSource{

    override suspend fun getListUser(userName: String): Flow<FetchResults<ListUserResponse>> {
       return flow {
            try {
                val data = apiService.getUserList(userName)
                emit(FetchResults.Success(data))
            }catch (e : Exception){
                emit(FetchResults.Error(e.toString()))
                Log.d("RemoteDataSource",e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getUserRepository(name: String): Flow<FetchResults<RepositoryUserResponse>> {
        return flow {
            try {
                emit(FetchResults.Success(apiService.getUserRepo(name)))
            }catch (e : Exception){
                emit(FetchResults.Error(e.toString()))
                Log.d("RemoteDataSource",e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getUserFollower(name: String): Flow<FetchResults<FollowerUserResponse>> {
        return flow{
            try {
                emit(FetchResults.Success(apiService.getUserFollowers(name)))
            }catch (e : Exception){
                emit(FetchResults.Error(e.toString()))
                Log.d("RemoteDataSource",e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }



    override fun getUserDetail(name: String): Call<DetailUserResponse> =
        apiService.getUserDetail(name)


    override fun getUserFollowing(name: String): Call<FollowerUserResponse> =
        apiService.getUserFollowing(name)


}