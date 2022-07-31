package com.example.core.data.local.source.githubapi

import com.example.core.data.local.dao.ListUserDao
import com.example.core.data.local.entity.userlist.GithubUserList
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

    override suspend fun insertListUser(user: List<GithubUserList>) {
        executorService.execute {
            localDao.insertListUser(user)
        }
    }

    override fun readListUser(): Flow<List<GithubUserList>> {
        return localDao.readListUser()
    }

    companion object{
        private val instance: LocalDataSource? = null
        fun getInstance(userDao: ListUserDao): LocalDataSource =
            instance ?: synchronized(this){
                instance ?: LocalDataSource(userDao)
            }
    }
}