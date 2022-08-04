package com.example.core.data.local.dao

import androidx.room.*
import com.example.core.data.local.entity.userlist.GithubListUser
import kotlinx.coroutines.flow.Flow

@Dao
interface ListUserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertListUser(user: List<GithubListUser>)

    @Query("select*from githublistuser order by id asc")
    fun readListUser(): Flow<List<GithubListUser>>

    @Query("delete from githublistuser")
    fun deleteListUser()
}