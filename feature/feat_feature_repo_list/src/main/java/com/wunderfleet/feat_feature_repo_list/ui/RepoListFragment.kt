package com.wunderfleet.feat_feature_repo_list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.wunderfleet.feat_domain_repo_list.model.GithubRepoModel
import com.wunderfleet.feat_feature_repo_list.R
import com.wunderfleet.feat_feature_repo_list.ui.adapter.RepoRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_repo_list.*

/**
 * A simple [Fragment] subclass.
 */
class RepoListFragment : Fragment() {

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var recyclerViewAdapter: RepoRecyclerViewAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repo_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val list = arguments?.get("repoList") as List<GithubRepoModel>
        recyclerViewAdapter = RepoRecyclerViewAdapter(list)
        setupViews()
    }

    fun setupViews() {
        layoutManager = LinearLayoutManager(activity?.applicationContext)
        repoList_recycler.layoutManager = layoutManager
        repoList_recycler.adapter = recyclerViewAdapter
    }
}
