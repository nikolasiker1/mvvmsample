package com.wunderfleet.core.extensions

import androidx.lifecycle.MutableLiveData
import com.wunderfleet.core.domain.resource.Resource

fun <T> MutableLiveData<Resource<T>>.setSuccess(data: T) =
    postValue(Resource(Resource.STATE.SUCCESS, data))

fun <T> MutableLiveData<Resource<T>>.setError(message: String? = null) =
    postValue(Resource(Resource.STATE.ERROR, value?.data, message))