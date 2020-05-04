package com.wunderfleet.feature_sampleapp.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.wunderfleet.feature_sampleapp.R
import com.wunderfleet.feature_sampleapp.model.UserModel
import kotlinx.android.synthetic.main.fragment_user_details.*

class UserFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupView()
    }

    private fun setupView() {
        val user = arguments?.get("user") as UserModel

        user.let {
            username.text = user.login
            userDetails_viewRepo.setOnClickListener(View.OnClickListener {
                val uri = Uri.parse("sampleapp://repoFragment")
                findNavController().navigate(uri)
            })
        }
    }
}