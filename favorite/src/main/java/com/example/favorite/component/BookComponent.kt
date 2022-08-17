package com.example.favorite.component

import android.content.Context
import com.example.core.di.BookModuleDepedencies
import com.example.core.di.FavoriteModuleDependecies
import com.example.favorite.BookFragment
import com.example.favorite.FavoriteFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [BookModuleDepedencies::class]
)
interface BookComponent {
    fun inject(fragment: BookFragment)

    @Component.Builder
    interface Builder{
        fun context(@BindsInstance context: Context): Builder
        fun appDepedencies(favoriteModuleDependecies: BookModuleDepedencies): Builder
        fun build(): BookComponent
    }
}