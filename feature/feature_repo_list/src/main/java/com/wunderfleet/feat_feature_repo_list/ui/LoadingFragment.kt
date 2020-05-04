package com.wunderfleet.feat_feature_repo_list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.wunderfleet.core.domain.resource.Resource
import com.wunderfleet.core.extensions.provideViewModel
import com.wunderfleet.feat_domain_repo_list.model.GithubRepoModel
import com.wunderfleet.feat_feature_repo_list.R
import com.wunderfleet.feat_feature_repo_list.di.injectors.RepoFeatureInjector
import com.wunderfleet.feat_feature_repo_list.viewmodel.LoadReposViewModel
import com.wunderfleet.feat_feature_repo_list.viewmodel.LoadReposViewModel.Companion.CONNECTION_ERROR
import kotlinx.android.synthetic.main.fragment_loading.*

/**
 * A simple [Fragment] subclass.
 */
class LoadingFragment : Fragment() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val loadReposViewModel by provideViewModel<LoadReposViewModel> {
        RepoFeatureInjector.component.getReposViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_loading, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loadRepos()
        initialiseViewModelObserver()
    }

    private fun initialiseViewModelObserver() {
        loadReposViewModel.getReposLiveData.observe(viewLifecycleOwner, Observer(::reposReceived))
    }

    private fun reposReceived(resource: Resource<List<GithubRepoModel>>?) {
        resource.let {
            when (it?.state) {
                Resource.STATE.LOADING -> onRepoFetchLoading()
                Resource.STATE.SUCCESS -> onRepoFetchSuccess(it)
                else -> onRepoFetchError(it)
            }
        }
    }

    private fun onRepoFetchError(resource: Resource<List<GithubRepoModel>>?) {
        when (resource?.message) {
            CONNECTION_ERROR -> setConnectionError()
            else -> setUnknownError()
        }
    }

    private fun setUnknownError() {
        viewLoading.visibility = View.GONE
        viewError.apply {
            visibility = View.VISIBLE
        }
    }

    private fun setConnectionError() {
        viewLoading.visibility = View.GONE
        viewError.apply {
            visibility = View.VISIBLE
        }
    }

    private fun onRepoFetchSuccess(resource: Resource<List<GithubRepoModel>>) {
        resource.data?.let { navigateToUser(it) }
    }

    private fun navigateToUser(repos: List<GithubRepoModel>) {
        val bundle = bundleOf("repoList" to repos)
        findNavController().navigate(
            R.id.action_loadingFragment_to_repoListFragment,
            bundle
        )
    }

    private fun onRepoFetchLoading() {
        viewLoading.visibility = View.VISIBLE
        viewError.visibility = View.GONE
    }

    private fun loadRepos() {
        loadReposViewModel.getReposLiveData("nikolasiker1")
    }

}
