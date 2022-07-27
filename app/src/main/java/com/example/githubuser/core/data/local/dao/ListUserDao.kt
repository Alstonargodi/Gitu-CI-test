package com.example.githubuser.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubuser.core.data.local.entity.userlist.GithubListUser
import kotlinx.coroutines.flow.Flow

@Dao
interface ListUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListUser(user: List<GithubListUser>)

    @Query("select*from githublistuser order by name desc")
    fun readListUser(): Flow<List<GithubListUser>>
}