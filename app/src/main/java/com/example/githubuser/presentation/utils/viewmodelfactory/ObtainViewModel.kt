package com.example.githubuser.viewmodel.util

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.githubuser.presentation.fragment.favorite.FavoriteViewModel
import com.example.githubuser.presentation.utils.viewmodelfactory.ViewModelFactory

fun obtainViewModel(activity: FragmentActivity): FavoriteViewModel {
    val factory = ViewModelFactory.getInstance(activity.application)
    return ViewModelProvider(activity,factory)[FavoriteViewModel::class.java]
}