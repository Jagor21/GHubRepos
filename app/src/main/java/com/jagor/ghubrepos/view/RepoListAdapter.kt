package com.jagor.ghubrepos.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jagor.ghubrepos.R
import com.jagor.ghubrepos.model.Repo
import com.jagor.ghubrepos.utils.formatDateFromString
import kotlinx.android.synthetic.main.repo_item_list.view.*

class RepoListAdapter(var repos: ArrayList<Repo>) :
    RecyclerView.Adapter<RepoListAdapter.RepoViewHolder>() {


    fun updateRepos(newRepos: List<Repo>) {
        repos.clear()
        repos.addAll(newRepos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder =
        RepoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.repo_item_list, parent, false)
        )

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(repos[position])
    }

    override fun getItemCount(): Int = repos.size


    class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val repoName = view.repo_name
        private val repoPrivate = view.repo_private
        private val repoLanguage = view.repo_language
        private val createdAt = view.created_at
        private val updatedAt = view.updated_at

        fun bind(repo: Repo) {
            repoName.text = repo.name
            repoPrivate.text = if (repo.private) "Private" else "Public"
            repoLanguage.text = repo.language
            createdAt.text = formatDateFromString(repo.createdAt)
            updatedAt.text = formatDateFromString(repo.updatedAt)
        }
    }
}