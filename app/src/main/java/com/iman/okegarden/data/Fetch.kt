package com.iman.okegarden.data

sealed class Fetch<out R> private constructor() {
    data class Success<out T>(val data: T) : Fetch<T>()
    data class Error(val error: String) : Fetch<Nothing>()
    object Loading : Fetch<Nothing>()

}