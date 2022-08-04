package com.example.core.data.local.dao

import androidx.room.*
import com.example.core.data.local.entity.remote.following.GithubUserFollower
import com.example.core.data.local.entity.userlist.GithubListUser
import com.example.core.data.local.entity.remote.userproject.GithubUserProject
import kotlinx.coroutines.flow.Flow

@Dao
interface RemoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertListUser(user: List<GithubListUser>)

    @Query("select*from githublistuser order by id asc")
    fun readListUser(): Flow<List<GithubListUser>>

    @Query("delete from githublistuser")
    fun deleteListUser()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUserRepository(data : List<GithubUserProject>)

    @Query("select*from githubuserproject order by id asc")
    fun readUserRepository(): Flow<List<GithubUserProject>>

    @Query("delete from githubuserproject")
    fun deleteUseRepository()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUserFollower(data : List<GithubUserFollower>)

    @Query("select*from githubuserfollower")
    fun readUserFollower(): Flow<List<GithubUserFollower>>

    @Query("delete from githubuserfollower")
    fun deleteUserFollower()
}