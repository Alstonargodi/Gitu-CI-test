package com.example.githubuser.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.githubuser.data.local.entity.FavoritePeople
import com.example.githubuser.data.local.entity.FavoriteProject

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



    @Delete
    fun deleteFavoriteProject(favoriteProject: FavoriteProject)
    @Delete
    fun deleteFavoritePeople(favoritePeople: FavoritePeople)


}