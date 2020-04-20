package com.wunderfleet.feature_sampleapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.wunderfleet.core.domain.resource.Resource
import com.wunderfleet.feature_sampleapp.R
import com.wunderfleet.feature_sampleapp.di.injectors.UserInjector
import com.wunderfleet.feature_sampleapp.model.UserModel
import com.wunderfleet.feature_sampleapp.viewmodel.LoadUserViewModel
import com.wunderfleet.feature_sampleapp.viewmodel.LoadUserViewModel.Companion.CONNECTION_ERROR
import kotlinx.android.synthetic.main.fragment_loading.*

class LoadingFragment : Fragment() {

    private val loadUserViewModel by viewModels<LoadUserViewModel> {
        UserInjector.component.getUserViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_loading, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loadUser()
        initialiseViewModelObserver()
    }


    private fun initialiseViewModelObserver() {
        loadUserViewModel.getUserLiveData.observe(viewLifecycleOwner, Observer(::userReceived))
    }

    private fun loadUser() {
        loadUserViewModel.getUserData()
    }

    private fun userReceived(resource: Resource<UserModel>?) {
        resource.let {
            when (it?.state) {
                Resource.STATE.LOADING -> onWeatherFetchLoading()
                Resource.STATE.SUCCESS -> onWeatherFetchSuccess(it)
                else -> onUserFetchError(it)
            }
        }
    }

    private fun onUserFetchError(resource: Resource<UserModel>?) {
        when (resource?.message) {
            CONNECTION_ERROR -> setConnectionError()
            else -> setUnknownError()
        }
    }

    private fun setUnknownError() {
        viewLoading.visibility = View.GONE
        viewError.apply {
            //setUnknownError()
            visibility = View.VISIBLE
        }
    }

    private fun setConnectionError() {
        viewLoading.visibility = View.GONE
        viewError.apply {
            //setConnectionError()
            visibility = View.VISIBLE
        }
    }

    private fun onWeatherFetchSuccess(resource: Resource<UserModel>) {
        resource.data?.let { navigateToUser(it) }
    }

    private fun onWeatherFetchLoading() {
        viewLoading.visibility = View.VISIBLE
        viewError.visibility = View.GONE
    }

    private fun navigateToUser(userModel: UserModel) {
        val bundle = bundleOf("user" to userModel)
        findNavController().navigate(
            R.id.action_loadingFragment_to_userFragment,
            bundle
        )
    }
}



