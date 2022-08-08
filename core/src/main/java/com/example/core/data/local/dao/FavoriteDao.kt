package com.example.core.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.core.data.local.entity.favorite.favoriteproject.FavoriteProject
import com.example.core.data.local.entity.favorite.favoriteuser.FavoriteUser
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteProject(favoriteProject: FavoriteProject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoritePeople(favorite: FavoriteUser)

    @Query("select*from FavoriteProject")
    fun readFavoriteProject(): Flow<List<FavoriteProject>>

    @Query("select*from FavoriteUser")
    fun readFavoritePeople():Flow<List<FavoriteUser>>

    @Query("select*from FavoriteUser where username like :name")
    fun searchFavoritePeople(name : String):Flow<List<FavoriteUser>>

    @Delete
    fun deleteFavoriteProject(favoriteProject: FavoriteProject)

    @Query("Delete from FavoriteUser where username like :name")
    fun deletePersonFavoritePeople(name: String)


}