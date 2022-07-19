package com.example.githubuser.presentation.fragment.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.githubuser.data.local.preference.SettingPreferences
import kotlinx.coroutines.launch

class SettingViewModel(private val pref : SettingPreferences): ViewModel() {

    fun getThemeSetting(): LiveData<Boolean> = pref.getThemesSetting().asLiveData()

    fun setThemeSetting(isDarkMode : Boolean){
        viewModelScope.launch {
            pref.setThemeSetting(isDarkMode)
        }
    }

}