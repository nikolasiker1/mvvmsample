package com.wunderfleet.feat_feature_repo_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.wunderfleet.core.domain.resource.Resource
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
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.Callable

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ViewModelUnitTest {

    @Mock
    lateinit var userRepository: GithubRepoRepository

    @Mock
    lateinit var schedulerProvider: SchedulerProvider

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var getReposUsecase: GetAllReposUsecase

    lateinit var loadReposViewModel: LoadReposViewModel

    private val githubRepoModel: GithubRepoModel = GithubRepoModel()


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { h: Callable<Scheduler?>? -> Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { h: Scheduler? -> Schedulers.trampoline() }
        githubRepoModel.name = "Nikola"
        whenever(schedulerProvider.getIoThread()).thenReturn(Schedulers.trampoline())
        whenever(schedulerProvider.getMainThread()).thenReturn(Schedulers.trampoline())
        getReposUsecase = GetAllReposUsecase(userRepository, schedulerProvider)
        loadReposViewModel = LoadReposViewModel(getReposUsecase)
    }

    @Test
    fun `null checks`() {
        assertNotNull(githubRepoModel)
        assertNotNull(userRepository)
        assertNotNull(schedulerProvider)
        assertNotNull(getReposUsecase)
        assertNotNull(loadReposViewModel)
    }

    @Test
    fun `succes usercase result`() {
        doReturn(Single.just(listOf(githubRepoModel))).`when`(userRepository)
            .getAllRepos("nikolasiker1")

        loadReposViewModel.getReposLiveData("nikolasiker1")


        verify(userRepository).getAllRepos("nikolasiker1")
        assertEquals(getReposUsecase.username, "nikolasiker1")
        assertEquals(
            loadReposViewModel.getReposLiveData.value?.data?.get(0)?.name, githubRepoModel.name
        )
        assertEquals(
            loadReposViewModel.getReposLiveData.value?.state, Resource.STATE.SUCCESS
        )
    }

    @Test
    fun `error usercase result`() {
        doReturn(Single.error<SocketTimeoutException>(SocketTimeoutException())).`when`(
            userRepository
        ).getAllRepos("error")

        loadReposViewModel.getReposLiveData("error")


        verify(userRepository).getAllRepos("error")
        assertEquals(loadReposViewModel.getReposLiveData.value?.state, Resource.STATE.ERROR)
        assertEquals(loadReposViewModel.getReposLiveData.value?.message, "connection_error")
    }


    @Test
    fun `unknown host error usercase result`() {
        doReturn(Single.error<UnknownHostException>(UnknownHostException())).`when`(
            userRepository
        ).getAllRepos("hosterror")

        loadReposViewModel.getReposLiveData("hosterror")


        verify(userRepository).getAllRepos("hosterror")
        assertEquals(loadReposViewModel.getReposLiveData.value?.state, Resource.STATE.ERROR)
        assertEquals(loadReposViewModel.getReposLiveData.value?.message, "connection_error")
    }

    @Test
    fun `connection error usercase result`() {
        doReturn(Single.error<ConnectException>(ConnectException())).`when`(
            userRepository
        ).getAllRepos("connectionerror")

        loadReposViewModel.getReposLiveData("connectionerror")


        verify(userRepository).getAllRepos("connectionerror")
        assertEquals(loadReposViewModel.getReposLiveData.value?.state, Resource.STATE.ERROR)
        assertEquals(loadReposViewModel.getReposLiveData.value?.message, "connection_error")
    }

    @Test
    fun `unknown error usercase result`() {
        doReturn(Single.error<Exception>(Exception())).`when`(
            userRepository
        ).getAllRepos("unknownerror")

        loadReposViewModel.getReposLiveData("unknownerror")


        verify(userRepository).getAllRepos("unknownerror")
        assertEquals(loadReposViewModel.getReposLiveData.value?.state, Resource.STATE.ERROR)
        assertEquals(loadReposViewModel.getReposLiveData.value?.message, null)
    }

    @Test
    fun `oncleared`() {
        loadReposViewModel.onCleared()
    }
}
