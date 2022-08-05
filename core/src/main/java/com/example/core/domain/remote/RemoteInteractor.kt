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
import com.example.core.domain.model.*
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

    override fun getUserDetail(name: String): Flow<Resource<List<UserDetail>>> =
        remoteRepository.getUserDetail(name)

    override fun getUserRepository(name: String): Flow<Resource<List<UserRepository>>> {
        return remoteRepository.getUserRepository(name)
    }

    override fun getUserFollowing(name: String): Flow<Resource<List<UserFollowing>>> {
       return remoteRepository.getUserFollowing(name)
    }

    override fun getUserFollower(name: String): Flow<Resource<List<UserFollower>>> {
        return remoteRepository.getUserFollower(name)
    }

    override fun deleteUserList() {
        remoteRepository.deleteListUser()
    }

    override fun deleteUserRepository() {
        remoteRepository.deleteUseRepository()
    }

    override fun deleteUserFollower() {
        remoteRepository.deleteUserFollower()
    }

    override fun deleteUserFollowing() {
        remoteRepository.deleteUserFollowing()
    }

    override fun deleteUserDetail() {
        remoteRepository.deleteUserDetail()
    }

    companion object{
        private const val TAG = "RemoteInteractor"
    }

}