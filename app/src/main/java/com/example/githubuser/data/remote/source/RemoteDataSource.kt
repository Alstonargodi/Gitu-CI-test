package com.example.githubuser.data.remote.source

import android.util.Log
import com.example.githubuser.data.remote.apiconfig.ApiConfig
import com.example.githubuser.data.remote.apiresponse.ListUserResponse
import com.example.githubuser.data.remote.apiresponse.coderepository.RepositoryUserResponse
import com.example.githubuser.data.remote.apiresponse.detail.DetailUserResponse
import com.example.githubuser.data.remote.apiresponse.follower.FollowerUserResponse
import com.example.githubuser.data.remote.apiservice.ApiService
import com.example.githubuser.data.remote.utils.FetchResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call

class RemoteDataSource(): IRemoteDataSource {
    private val apiService = ApiConfig.getApiService()

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


    override fun getUserDetail(name: String): Call<DetailUserResponse> =
        apiService.getUserDetail(name)

    override fun getUserRepository(name: String): Call<RepositoryUserResponse> =
        apiService.getUserRepo(name)

    override fun getUserFollowing(name: String): Call<FollowerUserResponse> =
        apiService.getUserFollowing(name)

    override fun getUserFollower(name: String): Call<FollowerUserResponse> =
        apiService.getUserFollowers(name)

}