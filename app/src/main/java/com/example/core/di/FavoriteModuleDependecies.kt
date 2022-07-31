package com.example.core.di

import com.example.core.domain.local.FavoriteUseCase
import com.example.core.domain.remote.RemoteUseCase
import dagger.Module
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface  FavoriteModuleDependecies {

    fun remoteUseCase(): RemoteUseCase
}