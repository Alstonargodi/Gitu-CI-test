package com.example.core.data.local.source.githubapi

import com.example.core.data.local.dao.ListUserDao
import com.example.core.data.local.entity.userlist.GithubListUser
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val localDao : ListUserDao
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


}