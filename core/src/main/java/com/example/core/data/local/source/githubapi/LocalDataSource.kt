package com.example.core.data.local.source.githubapi

import com.example.core.data.local.dao.RemoteDao
import com.example.core.data.local.entity.remote.following.GithubUserFollower
import com.example.core.data.local.entity.userlist.GithubListUser
import com.example.core.data.local.entity.remote.userproject.GithubUserProject
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val localDao : RemoteDao
): ILocalDataSource {

    private val executorService : ExecutorService = Executors.newSingleThreadExecutor()

    override suspend fun insertListUser(user: List<GithubListUser>) {
        executorService.execute {
            localDao.insertListUser(user)
        }
    }

    override fun readListUser(): Flow<List<GithubListUser>> {
        return localDao.readListUser()
    }

    override fun deleteListUser() {
        executorService.execute {
            localDao.deleteListUser()
        }
    }

    override suspend fun insertUserRepository(data: List<GithubUserProject>) {
        executorService.execute {
            localDao.insertUserRepository(data)
        }
    }

    override fun readUserRepository(): Flow<List<GithubUserProject>> {
        return localDao.readUserRepository()
    }

    override fun deleteUseRepository() {
        executorService.execute {
            localDao.deleteUseRepository()
        }
    }

    override suspend fun insertUserFollower(data: List<GithubUserFollower>) {
        executorService.execute {
            localDao.insertUserFollower(data)
        }
    }

    override fun readUserFollower(): Flow<List<GithubUserFollower>> {
       return localDao.readUserFollower()
    }

    override fun deleteUserFollower() {
      executorService.execute {
          localDao.deleteUserFollower()
      }
    }


}