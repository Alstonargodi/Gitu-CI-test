package com.example.core.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.core.data.local.entity.favorite.favoriteproject.FavoriteProject
import com.example.core.data.local.entity.favorite.favoriteuser.FavoriteUser

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteProject(favoriteProject: FavoriteProject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoritePeople(favorite: FavoriteUser)

    @Query("select*from FavoriteProject")
    fun readFavoriteProject(): LiveData<List<FavoriteProject>>

    @Query("select*from FavoriteUser")
    fun readFavoritePeople():LiveData<List<FavoriteUser>>

    @Query("select*from FavoriteUser where name like :name")
    fun searchFavoritePeople(name : String):LiveData<List<FavoriteUser>>

    @Delete
    fun deleteFavoriteProject(favoriteProject: FavoriteProject)

    @Query("Delete from FavoriteUser where name like :name")
    fun deletePersonFavoritePeople(name: String)


}