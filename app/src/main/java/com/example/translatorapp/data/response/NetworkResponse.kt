package com.example.translatorapp.data.response

sealed class NetworkResponse<T>(
    val data: T? = null, val message: String? = null
) {
    class Idle<T> : NetworkResponse<T>()
    class Loading<T> : NetworkResponse<T>()
    class Success<T>(data: T?) : NetworkResponse<T>(data = data)
    class Error<T>(message: String?) : NetworkResponse<T>(message = message)
}

sealed class RequestType {
    data object Get : RequestType()
    data class Post(val body: Any) : RequestType()
}