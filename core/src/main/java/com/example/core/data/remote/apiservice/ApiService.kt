package com.example.core.data.remote.apiservice
import com.example.core.BuildConfig
import com.example.core.data.local.entity.userproject.GithubUserProject
import com.example.core.data.remote.apiresponse.detail.DetailUserResponse
import com.example.core.data.remote.apiresponse.ListUserResponse
import com.example.core.data.remote.apiresponse.follower.FollowerUserResponse
import com.example.core.data.remote.apiresponse.coderepository.RepositoryUserResponse
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
    suspend fun getUserRepo(
        @Path("username") username : String,
    ): RepositoryUserResponse

}