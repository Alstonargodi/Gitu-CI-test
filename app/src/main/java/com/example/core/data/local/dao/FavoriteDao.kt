package com.example.core.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.core.data.local.entity.userlist.GithubUserList
import com.example.core.data.local.entity.githubrepository.GithubRepositoryList

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteProject(githubRepositoryList: GithubRepositoryList)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoritePeople(githubUserList: GithubUserList)

    @Query("select*from githubrepositorylist")
    fun readFavoriteProject(): LiveData<List<GithubRepositoryList>>

    @Query("select*from githubuserlist where isSaved = 1")
    fun readFavoritePeople():LiveData<List<GithubUserList>>

    @Query("select*from githubuserlist where name like :name and isSaved = 1")
    fun searchFavoritePeople(name : String):LiveData<List<GithubUserList>>

    @Delete
    fun deleteFavoriteProject(githubRepositoryList: GithubRepositoryList)

    @Query("Delete from githubuserlist where name like :name and isSaved = 1")
    fun deletePersonFavoritePeople(name: String)


}