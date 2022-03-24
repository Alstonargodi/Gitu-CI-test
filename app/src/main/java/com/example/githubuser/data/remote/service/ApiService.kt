package com.example.githubuser.data.remote.service
import com.example.githubuser.BuildConfig
import com.example.githubuser.data.remote.githubresponse.DetailResponse
import com.example.githubuser.data.remote.githubresponse.ListResponse
import com.example.githubuser.data.remote.githubresponse.follow.FollowResponse
import com.example.githubuser.data.remote.githubresponse.repository.RepoResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token ${BuildConfig.api_key}")
    fun getUserList(
        @Query("q") id : String
    ): Call<ListResponse>

    @GET("users/{username}")
    @Headers("Authorization: token ${BuildConfig.api_key}")
    fun getUserDetail(
        @Path("username") username : String,
    ): Call<DetailResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token ${BuildConfig.api_key}")
    fun getUserFollowers(
        @Path("username") username : String,
    ): Call<FollowResponse>

    @GET("users/{username}/following")
    @Headers("Authorization: token ${BuildConfig.api_key}")
    fun getUserFollowing(
        @Path("username") username : String,
    ): Call<FollowResponse>

    @GET("users/{username}/repos")
    @Headers("Authorization: token ${BuildConfig.api_key}")
    fun getUserRepo(
        @Path("username") username : String,
    ): Call<RepoResponse>

}