package com.example.githubuser.domain.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubuser.data.remote.apiresponse.ListUserResponse
import com.example.githubuser.data.remote.apiresponse.ListUserResponseItem
import com.example.githubuser.data.remote.apiresponse.coderepository.RepositoryUserResponse
import com.example.githubuser.data.remote.apiresponse.coderepository.RepositoryUserResponseItem
import com.example.githubuser.data.remote.apiresponse.detail.DetailUserResponse
import com.example.githubuser.data.remote.apiresponse.follower.FollowerUserResponse
import com.example.githubuser.data.remote.apiresponse.follower.FollowerUserResponseItem
import com.example.githubuser.data.repository.remote.IRemoteRepository
import com.example.githubuser.presentation.fragment.detail.DetailViewModel
import com.example.githubuser.presentation.fragment.following.FollowingViewModel
import com.example.githubuser.presentation.fragment.githubrepository.GithubRepositoryViewModel
import com.example.githubuser.presentation.fragment.home.HomeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteInteractor(private val remoteRepository: IRemoteRepository): RemoteUseCase {

    override fun getListUser(userName: String):LiveData<List<ListUserResponseItem>>{
        val userListResponse = MutableLiveData<List<ListUserResponseItem>>()

        remoteRepository.getListUser(userName).enqueue(object : Callback<ListUserResponse>{
            override fun onResponse(
                call: Call<ListUserResponse>,
                response: Response<ListUserResponse>
            ) {
                if (response.isSuccessful){
                    userListResponse.postValue(response.body()?.items)
                }else{
                    Log.d(HomeViewModel.TAG, response.message().toString())
                }
            }
            override fun onFailure(call: Call<ListUserResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        return userListResponse
    }

    override fun getUserDetail(name: String): LiveData<DetailUserResponse> {
        val detail = MutableLiveData<DetailUserResponse>()

        remoteRepository.getUserDetail(name).enqueue(object : Callback<DetailUserResponse>{
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                if (response.isSuccessful){
                    detail.postValue(response.body())
                }else{
                    Log.d(DetailViewModel.TAG, response.message().toString())
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        return detail
    }

    override fun getUserRepository(name: String): LiveData<List<RepositoryUserResponseItem>> {
        val githubRepo = MutableLiveData<List<RepositoryUserResponseItem>>()
        remoteRepository.getUserRepository(name).enqueue(object : Callback<RepositoryUserResponse>{
            override fun onResponse(
                call: Call<RepositoryUserResponse>,
                response: Response<RepositoryUserResponse>
            ) {
                if (response.isSuccessful){
                    githubRepo.value = response.body()
                }else{
                    Log.d(GithubRepositoryViewModel.TAG, response.message())
                }
            }
            override fun onFailure(call: Call<RepositoryUserResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        return githubRepo
    }

    override fun getUserFollowing(name: String): LiveData<List<FollowerUserResponseItem>> {
       val following = MutableLiveData<List<FollowerUserResponseItem>>()
        remoteRepository.getUserFollowing(name).enqueue(object : Callback<FollowerUserResponse>{
            override fun onResponse(
                call: Call<FollowerUserResponse>,
                response: Response<FollowerUserResponse>
            ) {
                if (response.isSuccessful){
                    following.value = response.body()
                }else{
                    Log.d(FollowingViewModel.TAG, response.message())
                }
            }
            override fun onFailure(call: Call<FollowerUserResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return following
    }

    override fun getUserFollower(name: String): LiveData<List<FollowerUserResponseItem>> {
        val follower = MutableLiveData<List<FollowerUserResponseItem>>()
        remoteRepository.getUserFollower(name).enqueue(object : Callback<FollowerUserResponse>{
            override fun onResponse(
                call: Call<FollowerUserResponse>,
                response: Response<FollowerUserResponse>
            ) {
                if (response.isSuccessful){
                    follower.value = response.body()
                }else{
                    Log.d(FollowingViewModel.TAG, response.message())
                }
            }

            override fun onFailure(call: Call<FollowerUserResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return follower
    }

}