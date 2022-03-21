package com.example.githubuser.viewmodel.util

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubuser.viewmodel.FavoriteViewModel


class ViewModelFactory private constructor(private val application: Application)
    : ViewModelProvider.NewInstanceFactory()
    {
        companion object{
            @Volatile
            private var instance: ViewModelFactory? = null
            fun getInstance(application: Application): ViewModelFactory {
                if (instance == null){
                    synchronized(ViewModelFactory::class.java){
                        instance = ViewModelFactory(application)
                    }
                }
                return instance as ViewModelFactory
            }
        }

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)){
                return FavoriteViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }

}