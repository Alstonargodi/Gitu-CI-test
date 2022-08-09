package com.example.core.data.remote.apiservice


import com.example.core.BuildConfig
import com.example.core.data.remote.apiresponse.ListUserResponse
import com.example.core.data.remote.apiresponse.coderepository.RepositoryUserResponse
import com.example.core.data.remote.apiresponse.detail.DetailUserResponse
import com.example.core.data.remote.apiresponse.follower.FollowerUserResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")

    suspend fun getUserList(
        @Query("q") id : String
    ): ListUserResponse

    @GET("users/{username}")
    @Headers("Authorization: token ${BuildConfig.api_key}")
    suspend fun getUserDetail(
        @Path("username") username : String,
    ): DetailUserResponse

    @GET("users/{username}/followers")
    @Headers("Authorization: token ${BuildConfig.api_key}")
    suspend fun getUserFollowers(
        @Path("username") username : String,
    ): FollowerUserResponse

    @GET("users/{username}/following")
    @Headers("Authorization: token ${BuildConfig.api_key}")
    suspend fun getUserFollowing(
        @Path("username") username : String,
    ): FollowerUserResponse

    @GET("users/{username}/repos")
    @Headers("Authorization: token ${BuildConfig.api_key}")
    suspend fun getUserRepo(
        @Path("username") username : String,
    ): RepositoryUserResponse

}