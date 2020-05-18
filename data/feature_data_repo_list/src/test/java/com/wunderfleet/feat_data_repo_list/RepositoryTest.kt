package com.wunderfleet.feat_data_repo_list

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.verify
import com.wunderfleet.core.rx.SchedulerProvider
import com.wunderfleet.feat_data_repo_list.repository.GithubReposRepositoryImpl
import com.wunderfleet.feat_data_repo_list.repository.ReposLocalDataSource
import com.wunderfleet.feat_data_repo_list.repository.ReposRemoteDataSource
import com.wunderfleet.feat_domain_repo_list.model.GithubRepoModel
import com.wunderfleet.feat_domain_repo_list.repository.GithubRepoRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.net.SocketTimeoutException
import java.util.concurrent.Callable


class RepositoryTest {

    val githubRepoModel = GithubRepoModel()

    @Mock
    lateinit var remoteDataSource: ReposRemoteDataSource

    @Mock
    lateinit var localDataSource: ReposLocalDataSource

    @Mock
    lateinit var schedulerProvider: SchedulerProvider

    lateinit var githubRepoRepository: GithubRepoRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { h: Callable<Scheduler?>? -> Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { h: Scheduler? -> Schedulers.trampoline() }
        githubRepoModel.name = "TestRepo"
        githubRepoRepository =
            spy(GithubReposRepositoryImpl(remoteDataSource, localDataSource, schedulerProvider))


        val repoList = mutableListOf(githubRepoModel)

        doReturn(Schedulers.trampoline()).`when`(schedulerProvider).getMainThread()

        doReturn(Schedulers.trampoline()).`when`(schedulerProvider).getIoThread()

        doReturn(Single.just(repoList)).`when`(localDataSource)
            .getAllRepos("nikolasiker1")

        doReturn(Single.just(repoList)).`when`(remoteDataSource)
            .getRepos("nikolasiker1")

        doReturn(Single.just(true)).`when`(localDataSource).insertRepo(any())

        doReturn(Single.error<SocketTimeoutException>(SocketTimeoutException())).`when`(
            githubRepoRepository
        )
            .getAllRepos("error")
    }

    @Test
    fun `repos return test`() {
        val repo = githubRepoRepository.getAllRepos("nikolasiker1");

        repo.map { it ->
            {
                assertEquals(it[0].name, "TestRepo")
                verify(localDataSource).insertRepo(it)
                localDataSource.insertRepo(it).test().assertValue(true)
                localDataSource.getAllRepos("nikolasiker1")
                verify(remoteDataSource).getRepos("nikolasiker1")
                verify(localDataSource).getAllRepos("nikolasiker1")
            }
        }
        repo.subscribe()

        repo.test().assertComplete()
        repo.test().assertNoErrors()
        repo.test().assertNoTimeout()
        verify(githubRepoRepository).getAllRepos("nikolasiker1")
        verify(schedulerProvider).getIoThread()
        verify(schedulerProvider).getMainThread()
    }

    @Test
    fun `repos return with error test`() {
        val repo = githubRepoRepository.getAllRepos("error");
        repo.subscribe()

        repo.test().assertNotComplete()
        repo.test().assertError(SocketTimeoutException::class.java)
    }

}
