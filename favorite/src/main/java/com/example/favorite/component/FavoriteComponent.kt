package com.example.favorite.component

import android.content.Context
import com.example.core.di.FavoriteModuleDependecies
import com.example.favorite.FavoriteFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [FavoriteModuleDependecies::class]
)
interface FavoriteComponent {
    fun inject(fragment: FavoriteFragment)
    
    @Component.Builder
    interface Builder{
        fun context(@BindsInstance context: Context): Builder
        fun appDepedencies(favoriteModuleDependecies: FavoriteModuleDependecies): Builder
        fun build(): FavoriteComponent
    }
}