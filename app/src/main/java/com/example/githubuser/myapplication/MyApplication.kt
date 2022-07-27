package com.example.githubuser.myapplication

import android.app.Application
import com.example.githubuser.core.injection.CoreComponent
import com.example.githubuser.core.injection.DaggerCoreComponent
import com.example.githubuser.di.AppComponent
import com.example.githubuser.di.DaggerAppComponent

open class MyApplication : Application() {
    private val coreComponent : CoreComponent by lazy{
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent : AppComponent by lazy{
        DaggerAppComponent.factory().create(coreComponent)
    }
}