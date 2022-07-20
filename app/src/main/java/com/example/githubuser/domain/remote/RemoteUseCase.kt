package com.example.githubuser.domain.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubuser.data.remote.apiresponse.ListUserResponse
import com.example.githubuser.data.remote.apiresponse.ListUserResponseItem
import com.example.githubuser.data.remote.apiresponse.coderepository.RepositoryUserResponse
import com.example.githubuser.data.remote.apiresponse.coderepository.RepositoryUserResponseItem
import com.example.githubuser.data.remote.apiresponse.detail.DetailUserResponse
import com.example.githubuser.data.remote.apiresponse.follower.FollowerUserResponse
import com.example.githubuser.data.remote.apiresponse.follower.FollowerUserResponseItem
import retrofit2.Call

interface RemoteUseCase {
    fun getListUser(userName : String): LiveData<List<ListUserResponseItem>>
    fun getUserDetail(name: String): LiveData<DetailUserResponse>
    fun getUserRepository(name : String): LiveData<List<RepositoryUserResponseItem>>
    fun getUserFollowing(name: String): LiveData<List<FollowerUserResponseItem>>
    fun getUserFollower(name: String): LiveData<List<FollowerUserResponseItem>>
}