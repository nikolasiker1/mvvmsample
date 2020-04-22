package com.wunderfleet.feat_feature_repo_list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wunderfleet.core.domain.resource.Resource

import com.wunderfleet.core.extensions.setError
import com.wunderfleet.core.extensions.setSuccess
import com.wunderfleet.feat_domain_repo_list.model.GithubRepoModel
import com.wunderfleet.feat_domain_repo_list.usecase.GetAllReposUsecase
import javax.inject.Inject

class LoadReposViewModel @Inject constructor(private val getAllReposUsecase: GetAllReposUsecase) : ViewModel() {
    private val _getReposLiveData = MutableLiveData<Resource<List<GithubRepoModel>>>()
    val getReposLiveData: LiveData<Resource<List<GithubRepoModel>>>
        get() = _getReposLiveData

    fun getReposLiveData() {
        getAllReposUsecase.run {
            clear()
            executeUseCase {
                handleResult(it)
            }
        }
    }

    private fun handleResult(status: GetAllReposUsecase.Status) {
        when (status) {
            is GetAllReposUsecase.Status.Success -> onUserSuccess(status)
            is GetAllReposUsecase.Status.ConnectionError -> onUserConnectionError()
            is GetAllReposUsecase.Status.UnknownError -> onUserUnknownError()
        }
    }

    private fun onUserUnknownError() {
        _getReposLiveData.setError()
    }

    private fun onUserConnectionError() {
        _getReposLiveData.setError(CONNECTION_ERROR)
    }

    private fun onUserSuccess(status: GetAllReposUsecase.Status.Success) {
        _getReposLiveData.setSuccess(status.githubRepoModel)
    }

    companion object {
        const val CONNECTION_ERROR = "connection_error"
    }

    override fun onCleared() {
        super.onCleared()
        getAllReposUsecase.clear()
    }
}