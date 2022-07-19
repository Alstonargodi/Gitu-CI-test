package com.example.githubuser.domain.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.githubuser.data.remote.apiresponse.ListUserResponse
import com.example.githubuser.data.remote.apiresponse.ListUserResponseItem
import com.example.githubuser.data.remote.apiresponse.coderepository.RepositoryUserResponse
import com.example.githubuser.data.remote.apiresponse.detail.DetailUserResponse
import com.example.githubuser.data.remote.apiresponse.follower.FollowerUserResponse
import com.example.githubuser.data.repository.RemoteRepository
import com.example.githubuser.data.repository.remote.IRemoteRepository
import com.example.githubuser.presentation.fragment.home.HomeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteInteractor(private val remoteRepository: IRemoteRepository): RemoteUseCase {

    val userListResponse = MutableLiveData<List<ListUserResponseItem>>()

    override fun getListUser(userName: String){
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
    }

    override fun getUserDetail(name: String): Call<DetailUserResponse> {
        TODO("Not yet implemented")
    }

    override fun getUserRepository(name: String): Call<RepositoryUserResponse> {
        TODO("Not yet implemented")
    }

    override fun getUserFollowing(name: String): Call<FollowerUserResponse> {
        TODO("Not yet implemented")
    }

    override fun getUserFollower(name: String): Call<FollowerUserResponse> {
        TODO("Not yet implemented")
    }

}