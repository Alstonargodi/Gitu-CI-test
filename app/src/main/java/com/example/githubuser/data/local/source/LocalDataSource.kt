package com.example.githubuser.data.local.source

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.githubuser.data.local.dao.ListUserDao
import com.example.githubuser.data.local.database.FavoriteDatabase
import com.example.githubuser.data.local.entity.userlist.GithubListUser
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class LocalDataSource(private val localDao : ListUserDao): ILocalDataSource {

    override suspend fun insertListUser(user: GithubListUser) {
        localDao.insertListUser(user)
    }

    override fun readListUser(): LiveData<List<GithubListUser>> {
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