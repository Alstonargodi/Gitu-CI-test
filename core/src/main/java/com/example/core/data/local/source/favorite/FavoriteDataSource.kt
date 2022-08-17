package com.example.core.data.local.source.favorite

import androidx.lifecycle.LiveData
import com.example.core.data.local.dao.FavoriteDao
import com.example.core.data.local.dao.RemoteDao
import com.example.core.data.local.entity.favorite.favoriteproject.FavoriteProject
import com.example.core.data.local.entity.favorite.favoriteuser.FavoriteUser
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteDataSource @Inject constructor(
    private val favoriteDao : FavoriteDao,
    private val remoteDao : RemoteDao
): IFavoriteDataSource {
    private val executorService : ExecutorService = Executors.newSingleThreadExecutor()

    override fun insertFavoriteProject(favoriteProject: FavoriteProject) {
        executorService.execute { favoriteDao.insertFavoriteProject(favoriteProject) }
    }

    override fun insertFavoritePeople(githubListUser: FavoriteUser) {
        executorService.execute { favoriteDao.insertFavoritePeople(githubListUser) }
    }

    override fun readFavoriteProject(): Flow<List<FavoriteProject>> =
        favoriteDao.readFavoriteProject()

    override fun readFavoritePeople(): Flow<List<FavoriteUser>> =
        favoriteDao.readFavoritePeople()

    override fun searchFavoritePeople(name: String): Flow<List<FavoriteUser>> =
        favoriteDao.searchFavoritePeople(name)

    override fun deleteFavoriteProject(favoriteProject: FavoriteProject) {
        executorService.execute {
            favoriteDao.deleteFavoriteProject(favoriteProject)
        }
    }

    override fun deletePersonFavoritePeople(name: String) {
       executorService.execute { favoriteDao.deletePersonFavoritePeople(name) }
    }

    override fun deleteUseRepository() {
        executorService.execute {
            remoteDao.deleteUseRepository()
        }
    }

    override fun deleteUserFollower() {
        executorService.execute {
            remoteDao.deleteUserFollower()
        }
    }

    override fun deleteUserFollowing() {
        executorService.execute {
            remoteDao.deleteUserFollowing()
        }
    }

    override fun deleteUserDetail() {
        executorService.execute {
            remoteDao.deleteUserDetail()
        }
    }


}