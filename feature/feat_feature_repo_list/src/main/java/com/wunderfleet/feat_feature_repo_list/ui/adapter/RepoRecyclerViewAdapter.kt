package com.wunderfleet.feat_feature_repo_list.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wunderfleet.feat_domain_repo_list.model.GithubRepoModel
import com.wunderfleet.feat_feature_repo_list.R
import com.wunderfleet.feat_feature_repo_list.model.RepoModel

class RepoRecyclerViewAdapter(private val repos: List<GithubRepoModel>) :
    RecyclerView.Adapter<RepoRecyclerViewAdapter.RepoViewHolder>() {
    class RepoViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
        inflater.inflate(
            R.layout.item_repo, parent, false
        )
    ) {

        private var mTitleView: TextView? = null


        init {
            mTitleView = itemView.findViewById(R.id.repoItem_repoName)
        }

        fun bindRepo(repo: GithubRepoModel) {
            mTitleView?.text = repo.name
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RepoViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = repos.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bindRepo(repos[position])
    }
}