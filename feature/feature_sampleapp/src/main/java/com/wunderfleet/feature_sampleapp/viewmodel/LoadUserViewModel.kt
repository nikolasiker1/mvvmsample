package com.wunderfleet.feature_sampleapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wunderfleet.core.domain.resource.Resource
import com.wunderfleet.core.extensions.setError
import com.wunderfleet.core.extensions.setSuccess
import com.wunderfleet.domain_sampleapp.usecase.GetUserUsecase
import com.wunderfleet.feature_sampleapp.model.UserModel
import com.wunderfleet.feature_sampleapp.model.mapToView
import javax.inject.Inject

open class LoadUserViewModel @Inject constructor(private val getUserUsecase: GetUserUsecase) :
    ViewModel() {

    private val _getUserLiveData = MutableLiveData<Resource<UserModel>>()
    val getUserLiveData: LiveData<Resource<UserModel>>
        get() = _getUserLiveData


    fun getUserData(username: String) {
        getUserUsecase.username = username
        getUserUsecase.run {
            clear()
            executeUseCase {
                handleResult(it)
            }
        }
    }

    private fun handleResult(status: GetUserUsecase.Status) {
        when (status) {
            is GetUserUsecase.Status.Success -> onUserSuccess(status)
            is GetUserUsecase.Status.ConnectionError -> onUserConnectionError()
            is GetUserUsecase.Status.UnknownError -> onUserUnknownError()
        }
    }

    private fun onUserUnknownError() {
        _getUserLiveData.setError()
    }

    private fun onUserConnectionError() {
        _getUserLiveData.setError(CONNECTION_ERROR)
    }

    private fun onUserSuccess(status: GetUserUsecase.Status.Success) {
        _getUserLiveData.setSuccess(status.githubUserModel.mapToView())
    }

    companion object {
        const val CONNECTION_ERROR = "connection_error"
    }

    override fun onCleared() {
        super.onCleared()
        getUserUsecase.clear()
    }
}