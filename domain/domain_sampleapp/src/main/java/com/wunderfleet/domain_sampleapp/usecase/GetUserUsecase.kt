package com.wunderfleet.domain_sampleapp.usecase

import com.wunderfleet.core.domain.usecase.UseCase
import com.wunderfleet.core.rx.SchedulerProvider
import com.wunderfleet.core.rx.disposeWith
import com.wunderfleet.domain_sampleapp.model.GithubUserModel
import com.wunderfleet.domain_sampleapp.repository.UserRepository
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class GetUserUsecase @Inject constructor(
    private val userRepository: UserRepository,
    private val schedulerProvider: SchedulerProvider
) : UseCase<GetUserUsecase.Status>() {
    sealed class Status {
        data class Success(val githubUserModel: GithubUserModel) : Status()
        object ConnectionError : Status()
        object UnknownError : Status()
    }

    override fun executeUseCase(onStatus: (status: Status) -> Unit) {
        userRepository.getUser("nikolasiker1")
            .map<Status> { Status.Success(it) }
            .onErrorReturn(::onError)
            .subscribeOn(schedulerProvider.io)
            .observeOn(schedulerProvider.mainThread)
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