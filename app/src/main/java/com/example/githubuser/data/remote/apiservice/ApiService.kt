package com.example.githubuser.data.remote.apiservice
import com.example.githubuser.BuildConfig
import com.example.githubuser.data.remote.apiresponse.detail.DetailUserResponse
import com.example.githubuser.data.remote.apiresponse.ListUserResponse
import com.example.githubuser.data.remote.apiresponse.follower.FollowerUserResponse
import com.example.githubuser.data.remote.apiresponse.coderepository.RepositoryUserResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("search/users")
    @Headers("Authorization: token ${BuildConfig.api_key}")
    fun getUserList(
        @Query("q") id : String
    ): Call<ListUserResponse>


    @GET("users/{username}")
    @Headers("Authorization: token ${BuildConfig.api_key}")
    fun getUserDetail(
        @Path("username") username : String,
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token ${BuildConfig.api_key}")
    fun getUserFollowers(
        @Path("username") username : String,
    ): Call<FollowerUserResponse>

    @GET("users/{username}/following")
    @Headers("Authorization: token ${BuildConfig.api_key}")
    fun getUserFollowing(
        @Path("username") username : String,
    ): Call<FollowerUserResponse>

    @GET("users/{username}/repos")
    @Headers("Authorization: token ${BuildConfig.api_key}")
    fun getUserRepo(
        @Path("username") username : String,
    ): Call<RepositoryUserResponse>

}