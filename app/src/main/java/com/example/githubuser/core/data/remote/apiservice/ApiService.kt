package com.example.githubuser.core.data.remote.apiservice
import com.example.githubuser.BuildConfig
import com.example.githubuser.core.data.remote.apiresponse.detail.DetailUserResponse
import com.example.githubuser.core.data.remote.apiresponse.ListUserResponse
import com.example.githubuser.core.data.remote.apiresponse.follower.FollowerUserResponse
import com.example.githubuser.core.data.remote.apiresponse.coderepository.RepositoryUserResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("search/users")
    @Headers("Authorization:${BuildConfig.api_key}")
    suspend fun getUserList(
        @Query("q") id : String
    ): ListUserResponse


    @GET("users/{username}")
    @Headers("Authorization:${BuildConfig.api_key}")
    fun getUserDetail(
        @Path("username") username : String,
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization:${BuildConfig.api_key}")
    fun getUserFollowers(
        @Path("username") username : String,
    ): Call<FollowerUserResponse>

    @GET("users/{username}/following")
    @Headers("Authorization:${BuildConfig.api_key}")
    fun getUserFollowing(
        @Path("username") username : String,
    ): Call<FollowerUserResponse>

    @GET("users/{username}/repos")
    @Headers("Authorization:${BuildConfig.api_key}")
    fun getUserRepo(
        @Path("username") username : String,
    ): Call<RepositoryUserResponse>

}