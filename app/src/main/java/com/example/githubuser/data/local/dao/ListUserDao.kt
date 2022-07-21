package com.example.githubuser.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubuser.data.local.entity.userlist.GithubListUser

@Dao
interface ListUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListUser(user: GithubListUser)

    @Query("select*from githublistuser")
    fun readListUser(): LiveData<List<GithubListUser>>
}