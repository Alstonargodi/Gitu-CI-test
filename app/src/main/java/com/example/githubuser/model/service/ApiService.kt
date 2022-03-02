package com.example.githubuser.model.service

import com.example.githubuser.model.githubresponse.DetailResponse
import com.example.githubuser.model.githubresponse.ListResponse
import com.example.githubuser.model.githubresponse.follower.FollowerResponse
import com.example.githubuser.model.githubresponse.following.FollowingResponse
import com.example.githubuser.model.githubresponse.repository.RepoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    fun getUserList(
        @Query("q") id : String
    ): Call<ListResponse>

    @GET("users/{nama}")
    fun getUserDetail(
        @Path("nama") nama : String,
    ): Call<DetailResponse>

    @GET("users/{username}/followers")
    fun getUserFollowers(
        @Path("username") username : String
    ): Call<FollowerResponse>

    @GET("users/{username}/following")
    fun getUserFollowing(
        @Path("username") username : String
    ): Call<FollowingResponse>

    @GET("users/{username}/repos")
    fun getUserRepo(
        @Path("username") username : String
    ): Call<RepoResponse>



}