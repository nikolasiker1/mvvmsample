package com.wunderfleet.feat_domain_repo_list.usecase

import com.wunderfleet.core.domain.usecase.UseCase
import com.wunderfleet.core.rx.SchedulerProvider
import com.wunderfleet.core.rx.disposeWith
import com.wunderfleet.feat_domain_repo_list.model.GithubRepoModel
import com.wunderfleet.feat_domain_repo_list.repository.GithubRepoRepository
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class GetAllReposUsecase @Inject constructor(
    private val githubRepoRepository: GithubRepoRepository,
    private val schedulerProvider: SchedulerProvider
) : UseCase<GetAllReposUsecase.Status>() {

    var username: String = ""

    sealed class Status {
        data class Success(val githubRepoModel: List<GithubRepoModel>) : Status()
        object ConnectionError : Status()
        object UnknownError : Status()
    }

    override fun executeUseCase(onStatus: (status: Status) -> Unit) {
        githubRepoRepository
            .getAllRepos(username)
            .map<Status> {
                Status.Success(it)
            }
            .onErrorReturn(::onError)
            .subscribeOn(schedulerProvider.getIoThread())
            .observeOn(schedulerProvider.getMainThread())
            .subscribe(onStatus)
            .disposeWith(compositeDisposable)
    }

    private fun onError(throwable: Throwable): Status {
        throwable.printStackTrace()
        return when (throwable) {
            is SocketTimeoutException -> Status.ConnectionError
            is UnknownHostException -> Status.ConnectionError
            is ConnectException -> Status.ConnectionError
            else -> {
                Status.UnknownError
            }
        }
    }
}