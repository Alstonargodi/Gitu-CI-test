package com.example.githubuser.presentation.utils.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubuser.data.local.preference.SettingPreferences
import com.example.githubuser.presentation.fragment.setting.SettingViewModel

@Suppress("UNCHECKED_CAST")
class SettingViewModelFactory(private val pref : SettingPreferences)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingViewModel::class.java)){
            return SettingViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}