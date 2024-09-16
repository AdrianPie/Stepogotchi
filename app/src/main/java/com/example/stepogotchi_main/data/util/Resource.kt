package com.example.stepogotchi_main.data.util

sealed class Resource<T>(val data: T? = null, val message: String? = null, val success: Boolean?) {
    class Success<T>(data: T): Resource<T>(data, success = true)
    class Failure<T>(data: T? = null, message: String?): Resource<T>(data, message, false)
    class Loading<T>(data: T? = null): Resource<T>(data, success = false)

}