package com.example.githubuser.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.githubuser.data.local.entity.favoritepeople.FavoritePeople
import com.example.githubuser.data.local.entity.favoriteproject.FavoriteProject

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteProject(favoriteProject: FavoriteProject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoritePeople(favoritePeople: FavoritePeople)

    @Query("select*from favoriteproject")
    fun readFavoriteProject(): LiveData<List<FavoriteProject>>

    @Query("select*from favoritepeople")
    fun readFavoritePeople():LiveData<List<FavoritePeople>>

    @Query("select*from favoritepeople where name like :name")
    fun searchFavoritePeople(name : String):LiveData<List<FavoritePeople>>

    @Delete
    fun deleteFavoriteProject(favoriteProject: FavoriteProject)

    @Query("Delete from favoritepeople where name like :name")
    fun deletePersonFavoritePeople(name: String)


}