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
import com.example.core.domain.model.UserFollower
import com.example.core.domain.model.UserRepository
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

    override fun showHistoryListUser(): Flow<List<ListUser>> =
        remoteRepository.showHistoryListUser()


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
                    Log.d(TAG, response.message().toString())
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                Log.d(TAG,t.toString())
            }
        })
        return detail
    }

    override fun getUserRepository(name: String): Flow<Resource<List<UserRepository>>> {
        return remoteRepository.getUserRepository(name)
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
                    Log.d(TAG, response.message())
                }
            }
            override fun onFailure(call: Call<FollowerUserResponse>, t: Throwable) {
                Log.d(TAG,t.toString())
            }

        })
        return following
    }

    override fun getUserFollower(name: String): Flow<Resource<List<UserFollower>>> {
        return remoteRepository.getUserFollower(name)
    }

    override fun deleteUserRepository() {
        remoteRepository.deleteUseRepository()
    }

    override fun deleteUserFollower() {
        remoteRepository.deleteUserFollower()
    }

    companion object{
        private const val TAG = "RemoteInteractor"
    }

}