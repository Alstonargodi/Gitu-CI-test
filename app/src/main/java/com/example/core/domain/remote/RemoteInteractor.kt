package com.example.core.domain.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.data.remote.apiresponse.coderepository.RepositoryUserResponse
import com.example.core.data.remote.apiresponse.coderepository.RepositoryUserResponseItem
import com.example.core.data.remote.apiresponse.detail.DetailUserResponse
import com.example.core.data.remote.apiresponse.follower.FollowerUserResponse
import com.example.core.data.remote.apiresponse.follower.FollowerUserResponseItem
import com.example.core.data.remote.utils.Resource
import com.example.core.data.repository.remote.IRemoteRepository
import com.example.core.domain.model.ListUser
import com.example.githubuser.presentation.fragment.detail.DetailViewModel
import com.example.githubuser.presentation.fragment.following.FollowingViewModel
import com.example.githubuser.presentation.fragment.repositoryuser.GithubRepositoryViewModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RemoteInteractor @Inject constructor(
    private val remoteRepository: IRemoteRepository
): RemoteUseCase {

    override fun getListUser(userName: String): Flow<Resource<List<ListUser>>> =
        remoteRepository.getListUser(userName)

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

    override fun deleteListUser() {
        remoteRepository.deleteListUser()
    }

}