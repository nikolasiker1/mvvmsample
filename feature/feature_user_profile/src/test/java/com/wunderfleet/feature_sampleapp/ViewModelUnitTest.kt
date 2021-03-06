package com.wunderfleet.feature_sampleapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.wunderfleet.core.domain.resource.Resource
import com.wunderfleet.core.rx.SchedulerProvider
import com.wunderfleet.domain_sampleapp.model.GithubUserModel
import com.wunderfleet.domain_sampleapp.repository.UserRepository
import com.wunderfleet.domain_sampleapp.usecase.GetUserUsecase
import com.wunderfleet.feature_sampleapp.viewmodel.LoadUserViewModel
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
    lateinit var userRepository: UserRepository

    @Mock
    lateinit var schedulerProvider: SchedulerProvider

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var getUsersUsecase: GetUserUsecase

    lateinit var loadUserViewModel: LoadUserViewModel

    private val githubUserModel: GithubUserModel = GithubUserModel()


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { h: Callable<Scheduler?>? -> Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { h: Scheduler? -> Schedulers.trampoline() }
        githubUserModel.name = "Nikola"

        whenever(schedulerProvider.getIoThread()).thenReturn(Schedulers.trampoline())
        whenever(schedulerProvider.getMainThread()).thenReturn(Schedulers.trampoline())
        getUsersUsecase = GetUserUsecase(userRepository, schedulerProvider)
        loadUserViewModel = LoadUserViewModel(getUsersUsecase)
    }

    @Test
    fun `null checks`() {
        assertNotNull(githubUserModel)
        assertNotNull(userRepository)
        assertNotNull(schedulerProvider)
        assertNotNull(getUsersUsecase)
        assertNotNull(loadUserViewModel)
    }

    @Test
    fun `succes usercase result`() {
        doReturn(Single.just(githubUserModel)).`when`(userRepository).getUser("nikolasiker1")

        loadUserViewModel.getUserData("nikolasiker1")


        verify(userRepository).getUser("nikolasiker1")
        assertEquals(getUsersUsecase.username, "nikolasiker1")
        assertEquals(
            loadUserViewModel.getUserLiveData.value?.data?.name, githubUserModel.name
        )
        assertEquals(
            loadUserViewModel.getUserLiveData.value?.state, Resource.STATE.SUCCESS
        )
    }

    @Test
    fun `error usercase result`() {
        doReturn(Single.error<SocketTimeoutException>(SocketTimeoutException())).`when`(
            userRepository
        ).getUser("error")
        loadUserViewModel.getUserData("error")


        verify(userRepository).getUser("error")
        assertEquals(loadUserViewModel.getUserLiveData.value?.state, Resource.STATE.ERROR)
        assertEquals(loadUserViewModel.getUserLiveData.value?.message, "connection_error")
    }


    @Test
    fun `unknown host error usercase result`() {
        doReturn(Single.error<UnknownHostException>(UnknownHostException())).`when`(
            userRepository
        ).getUser("hosterror")

        loadUserViewModel.getUserData("hosterror")


        verify(userRepository).getUser("hosterror")
        assertEquals(loadUserViewModel.getUserLiveData.value?.state, Resource.STATE.ERROR)
        assertEquals(loadUserViewModel.getUserLiveData.value?.message, "connection_error")
    }

    @Test
    fun `connection error usercase result`() {
        doReturn(Single.error<ConnectException>(ConnectException())).`when`(
            userRepository
        ).getUser("connectionerror")

        loadUserViewModel.getUserData("connectionerror")


        verify(userRepository).getUser("connectionerror")
        assertEquals(loadUserViewModel.getUserLiveData.value?.state, Resource.STATE.ERROR)
        assertEquals(loadUserViewModel.getUserLiveData.value?.message, "connection_error")
    }

    @Test
    fun `unknown error usercase result`() {
        doReturn(Single.error<Exception>(Exception())).`when`(
            userRepository
        ).getUser("unknownerror")

        loadUserViewModel.getUserData("unknownerror")


        verify(userRepository).getUser("unknownerror")
        assertEquals(loadUserViewModel.getUserLiveData.value?.state, Resource.STATE.ERROR)
        assertEquals(loadUserViewModel.getUserLiveData.value?.message, null)
    }

    @Test
    fun `oncleared`() {
        loadUserViewModel.onCleared()
    }
}
