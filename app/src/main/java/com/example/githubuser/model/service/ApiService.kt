package com.example.githubuser.model.service

import com.example.githubuser.model.githubresponse.DetailResponse
import com.example.githubuser.model.githubresponse.ListResponse
import com.example.githubuser.model.githubresponse.follower.FollowerResponse
import com.example.githubuser.model.githubresponse.following.FollowingResponse
import com.example.githubuser.model.githubresponse.repository.RepoResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getUserList(
        @Query("q") id : String,
    ): Call<ListResponse>

    @GET("users/{nama}")
    @Headers("Authorization: token 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")
    fun getUserDetail(
        @Path("nama") nama : String,
    ): Call<DetailResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getUserFollowers(
        @Path("username") username : String,
    ): Call<FollowerResponse>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getUserFollowing(
        @Path("username") username : String,
    ): Call<FollowingResponse>

    @GET("users/{username}/repos")
    @Headers("Authorization: token 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")
    fun getUserRepo(
        @Path("username") username : String,
    ): Call<RepoResponse>


}