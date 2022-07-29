package com.example.core.injection

object Injection {
//
//    fun provideLocalUseCase(context: Context): FavoriteUseCase {
//        return FavoriteInteractor(provideRepository(context))
//    }
//
//    private fun provideRepository(context: Context): IFavoriteRepository{
//        return FavoriteRepository(provideDataSource(context))
//    }
//
//    private fun provideDataSource(context: Context): IFavoriteDataSource{
//        return FavoriteDataSource(LocalDatabase.setDatabase(context).favoriteDao())
//    }
//
//    fun provideRemoteUseCase(context: Context): RemoteUseCase{
//        return RemoteInteractor(
//            provideRemoteRepository(context)
//        )
//    }
//
//    private fun provideRemoteRepository(context: Context): IRemoteRepository{
//        val database = LocalDatabase.setDatabase(context)
//        return RemoteRepository(
//            provideRemoteSource(),
//            LocalDataSource.getInstance(database.listUserDao())
//        )
//    }
//
//    private fun provideRemoteSource(): IRemoteDataSource{
//        return RemoteDataSource(ApiConfig.getApiService())
//    }


}