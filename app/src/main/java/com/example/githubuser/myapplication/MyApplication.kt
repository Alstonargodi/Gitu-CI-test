package com.example.githubuser.myapplication

import android.app.Application
import com.example.core.injection.CoreComponent
import com.example.core.injection.DaggerCoreComponent
import com.example.core.di.AppComponent
import com.example.core.di.DaggerAppComponent

open class MyApplication : Application() {
    private val coreComponent : CoreComponent by lazy{
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent : AppComponent by lazy{
        DaggerAppComponent.factory().create(coreComponent)
    }
}