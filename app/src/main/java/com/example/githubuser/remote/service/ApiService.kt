package com.example.githubuser.remote.service
import com.example.githubuser.remote.githubresponse.DetailResponse
import com.example.githubuser.remote.githubresponse.ListResponse
import com.example.githubuser.remote.githubresponse.follower.FollowerResponse
import com.example.githubuser.remote.githubresponse.following.FollowingResponse
import com.example.githubuser.remote.githubresponse.repository.RepoResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")

    fun getUserList(
        @Query("q") id : String
    ): Call<ListResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetail(
        @Path("username") username : String,
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