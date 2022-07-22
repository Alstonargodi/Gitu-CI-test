package com.example.githubuser.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubuser.data.local.entity.userlist.GithubListUser
import kotlinx.coroutines.flow.Flow

@Dao
interface ListUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListUser(user: List<GithubListUser>)

    @Query("select*from githublistuser")
    fun readListUser(): Flow<List<GithubListUser>>
}