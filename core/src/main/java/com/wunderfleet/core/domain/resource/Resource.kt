package com.wunderfleet.core.domain.resource

open class Resource<T>(
    val state: STATE? = null,
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(state: STATE, data: T) : Resource<T>(state, data)
    class Loading<T>(state: STATE, data: T? = null) : Resource<T>(state, data)
    class Error<T>(state: STATE, message: String, data: T? = null) :
        Resource<T>(state, data, message)

    enum class STATE {
        SUCCESS, ERROR, LOADING
    }
}