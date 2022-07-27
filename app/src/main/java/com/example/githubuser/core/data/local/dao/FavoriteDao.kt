package com.example.githubuser.core.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.githubuser.core.data.local.entity.userlist.GithubListUser
import com.example.githubuser.core.data.local.entity.favoriteproject.FavoriteProject

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteProject(favoriteProject: FavoriteProject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoritePeople(githubListUser: GithubListUser)

    @Query("select*from favoriteproject")
    fun readFavoriteProject(): LiveData<List<FavoriteProject>>

    @Query("select*from githublistuser where isSaved = 1")
    fun readFavoritePeople():LiveData<List<GithubListUser>>

    @Query("select*from githublistuser where name like :name and isSaved = 1")
    fun searchFavoritePeople(name : String):LiveData<List<GithubListUser>>

    @Delete
    fun deleteFavoriteProject(favoriteProject: FavoriteProject)

    @Query("Delete from githublistuser where name like :name and isSaved = 1")
    fun deletePersonFavoritePeople(name: String)


}