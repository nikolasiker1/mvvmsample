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
import java.net.SocketTimeoutException
import java.util.concurrent.Callable

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ViewModelUnitTest {

    @Mock
    lateinit var repoRepository: GithubRepoRepository

    @Mock
    lateinit var schedulerProvider: SchedulerProvider

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var getAllReposUsecase: GetAllReposUsecase

    lateinit var loadReposViewModel: LoadReposViewModel

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
        getAllReposUsecase = GetAllReposUsecase(repoRepository, schedulerProvider)
        loadReposViewModel = LoadReposViewModel(getAllReposUsecase)
    }

    @Test
    fun `null checks`() {
        assertNotNull(githubRrepoModel)
        assertNotNull(repoRepository)
        assertNotNull(schedulerProvider)
        assertNotNull(getAllReposUsecase)
        assertNotNull(loadReposViewModel)
    }

    @Test
    fun `succes usercase result`() {
        loadReposViewModel.getReposLiveData("nikolasiker1")


        verify(repoRepository).getAllRepos("nikolasiker1")
        assertEquals(getAllReposUsecase.username, "nikolasiker1")
        assertEquals(
            loadReposViewModel.getReposLiveData.value?.data?.get(0)?.name, githubRrepoModel.name
        )
        assertEquals(
            loadReposViewModel.getReposLiveData.value?.state, Resource.STATE.SUCCESS
        )
    }

    @Test
    fun `error usercase result`() {
        loadReposViewModel.getReposLiveData("error")


        verify(repoRepository).getAllRepos("error")
        assertEquals(loadReposViewModel.getReposLiveData.value?.state, Resource.STATE.ERROR)
        assertEquals(loadReposViewModel.getReposLiveData.value?.message, "connection_error")
    }
}
