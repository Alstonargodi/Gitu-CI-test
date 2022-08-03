package com.example.core.data.local.dao

import androidx.room.*
import com.example.core.data.local.entity.userlist.GithubUserList
import kotlinx.coroutines.flow.Flow

@Dao
interface ListUserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertListUser(user: List<GithubUserList>)

    @Query("select*from githubuserlist order by id asc")
    fun readListUser(): Flow<List<GithubUserList>>

    @Query("delete from githubuserlist")
    fun deleteListUser()
}