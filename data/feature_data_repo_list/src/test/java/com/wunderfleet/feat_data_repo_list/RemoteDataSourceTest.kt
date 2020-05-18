package com.wunderfleet.feat_data_repo_list

import com.nhaarman.mockito_kotlin.doReturn
import com.wunderfleet.feat_data_repo_list.remote.ReposRemoteDataSourceImpl
import com.wunderfleet.feat_domain_repo_list.model.GithubRepoModel
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class RemoteDataSourceTest {

    @Mock
    lateinit var reposRemoteDataSource: ReposRemoteDataSourceImpl

    val githubRepoModel = GithubRepoModel()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        githubRepoModel.name = "test"

        doReturn(Single.just(mutableListOf(githubRepoModel))).`when`(reposRemoteDataSource)
            .getRepos("nikolasiker1")
    }

    @Test
    fun testRepoExecution() {
        val request = reposRemoteDataSource.getRepos("nikolasiker1")
        request.subscribe { it ->
            assertEquals(it[0].name, githubRepoModel.name)
        }

        request.test().assertComplete()
    }
}