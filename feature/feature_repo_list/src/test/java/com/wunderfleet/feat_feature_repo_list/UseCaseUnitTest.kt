package com.wunderfleet.feat_feature_repo_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.*
import com.wunderfleet.core.rx.SchedulerProvider
import com.wunderfleet.feat_domain_repo_list.model.GithubRepoModel
import com.wunderfleet.feat_domain_repo_list.repository.GithubRepoRepository
import com.wunderfleet.feat_domain_repo_list.usecase.GetAllReposUsecase
import com.wunderfleet.feat_feature_repo_list.viewmodel.LoadReposViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.net.SocketTimeoutException
import java.util.concurrent.Callable

class UseCaseUnitTest {
    @Mock
    lateinit var repoRepository: GithubRepoRepository

    @Mock
    lateinit var schedulerProvider: SchedulerProvider

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var getAllReposUsecase: GetAllReposUsecase

    private val githubRrepoModel: GithubRepoModel = GithubRepoModel()




    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { h: Callable<Scheduler?>? -> Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { h: Scheduler? -> Schedulers.trampoline() }
        githubRrepoModel.name = "test"
        doReturn(Single.just(mutableListOf(githubRrepoModel))).`when`(repoRepository)
            .getAllRepos("nikolasiker1")
        doReturn(Single.error<SocketTimeoutException>(SocketTimeoutException())).`when`(
            repoRepository
        ).getAllRepos("error")
        whenever(schedulerProvider.getIoThread()).thenReturn(Schedulers.trampoline())
        whenever(schedulerProvider.getMainThread()).thenReturn(Schedulers.trampoline())
        getAllReposUsecase = spy(GetAllReposUsecase(repoRepository, schedulerProvider))
    }

    @Test
    fun `isUseCaseExecutedOnSuccess`() {
        getAllReposUsecase.username = "nikolasiker1"
        getAllReposUsecase.executeUseCase {
            verify(getAllReposUsecase).executeUseCase(any())
            verify(getAllReposUsecase, times(1)).executeUseCase(any())
        }
    }

    @Test
    fun `isUseCaseExecutedOnError`() {
        getAllReposUsecase.username = "error"
        getAllReposUsecase.executeUseCase {
            verify(getAllReposUsecase).executeUseCase(any())
            verify(getAllReposUsecase, times(1)).executeUseCase(any())
        }
    }
}