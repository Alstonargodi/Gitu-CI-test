package com.example.core.data.local.dao

import androidx.room.*
import com.example.core.data.local.entity.remote.userdetail.GithubUserDetail
import com.example.core.data.local.entity.remote.userfollower.GithubUserFollower
import com.example.core.data.local.entity.remote.userfollowing.GithubUserFollowing
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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserFollowing(data : List<GithubUserFollowing>)

    @Query("select*from githubuserfollowing")
    fun readUserFollowing(): Flow<List<GithubUserFollowing>>

    @Query("delete from githubuserfollowing")
    fun deleteUserFollowing()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserDetail(data : List<GithubUserDetail>)

    @Query("select*from githubuserdetail")
    fun readUserDetail(): Flow<List<GithubUserDetail>>

    @Query("delete from githubuserdetail")
    fun deleteUserDetail()


}