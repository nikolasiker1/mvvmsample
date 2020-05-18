package com.wunderfleet.feat_feature_repo_list

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wunderfleet.core.extensions.replace
import com.wunderfleet.feat_feature_repo_list.ui.LoadingFragment
import com.wunderfleet.feat_feature_repo_list.viewmodel.LoadReposViewModel
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.any
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class LoadingFragmentTest {

    val viewModelMock = mock(LoadReposViewModel::class.java)
    val tested = LoadingFragment().apply {
        replace(LoadingFragment::loadReposViewModel, viewModelMock)
    }

    @Test
    fun launchFragmentTest() {
        tested.onStart()
    }
}