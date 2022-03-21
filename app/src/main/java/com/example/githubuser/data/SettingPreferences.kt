package com.example.githubuser.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit

class SettingPreferences private constructor(
    private val dataStore: DataStore<Preferences>
) {
    private val themeKey = booleanPreferencesKey("theme_setting")


    fun getThemesSetting(): Flow<Boolean>{
        return dataStore.data.map { prefrences ->
            prefrences[themeKey] ?: false
        }
    }

    suspend fun setThemeSetting(isDarkMode : Boolean){
        dataStore.edit { refrences ->
            refrences[themeKey] = isDarkMode
        }
    }

    companion object{
        @Volatile
        private var INSTANCE : SettingPreferences? = null

        fun getInstance(dataStore: DataStore<Preferences>): SettingPreferences{
            return INSTANCE ?: synchronized(this){
                val instance = SettingPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}