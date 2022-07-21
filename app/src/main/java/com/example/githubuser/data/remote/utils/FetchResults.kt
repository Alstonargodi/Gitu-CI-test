package com.example.githubuser.data.remote.utils

sealed class FetchResults<out R> {
    data class Success<out T>(val data: T) : FetchResults<T>()
    data class Error(val errorMessage: String) : FetchResults<Nothing>()
    object Empty : FetchResults<Nothing>()
}