package com.example.githubuser.viewmodel.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.githubuser.viewmodel.FavoriteViewModel
import com.example.githubuser.viewmodel.MainViewModel

fun obtainViewModel(activity: FragmentActivity): FavoriteViewModel{
    val factory = ViewModelFactory.getInstance(activity.application)
    return ViewModelProvider(activity,factory)[FavoriteViewModel::class.java]
}