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
import com.example.githubuser.data.remote.utils.FetchResults
import com.example.githubuser.data.remote.utils.Resource
import com.example.githubuser.domain.model.ListUser
import kotlinx.coroutines.flow.Flow
import retrofit2.Call

interface RemoteUseCase {
    fun getListUser(userName : String): Flow<Resource<List<ListUser>>>
    fun getUserDetail(name: String): LiveData<DetailUserResponse>
    fun getUserRepository(name : String): LiveData<List<RepositoryUserResponseItem>>
    fun getUserFollowing(name: String): LiveData<List<FollowerUserResponseItem>>
    fun getUserFollower(name: String): LiveData<List<FollowerUserResponseItem>>
}