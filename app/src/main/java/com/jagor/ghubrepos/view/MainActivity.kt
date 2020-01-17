package com.jagor.ghubrepos.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jagor.ghubrepos.R
import com.jagor.ghubrepos.model.Owner
import com.jagor.ghubrepos.utils.loadImage
import com.jagor.ghubrepos.utils.onDrawableClick
import com.jagor.ghubrepos.viewModel.RepoListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: RepoListViewModel
    private var repoListAdapter: RepoListAdapter = RepoListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(RepoListViewModel::class.java)

        search_field.onDrawableClick {
            viewModel.getReposByUser(it.text.toString())
        }

        repos_list.apply {
            this.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            this.adapter = repoListAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.repos.observe(this, Observer { repos ->
            repos?.let {
                repos_list.visibility = View.VISIBLE
                repoListAdapter.updateRepos(it)
            }
        })
        viewModel.repoLoadError.observe(this, Observer { isError ->
            isError?.let {
                listError.visibility = if (it) View.VISIBLE else View.GONE
                owner_details_container.visibility = if (it) View.GONE else View.VISIBLE
            }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loading_spinner.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    listError.visibility = View.GONE
                    repos_list.visibility = View.GONE
                }
            }
        })

        viewModel.owner.observe(this, Observer { owner ->
            owner?.let {
                inflateOwnerDetails(owner)
            }

        })
    }

    private fun inflateOwnerDetails(owner: Owner) {
        owner_details_container.visibility = View.VISIBLE
        owner_avatar.loadImage(owner.avatarUrl)
        owner_avatar.visibility = View.VISIBLE
        owner_name.text = owner.login
        public_repos_count.text = owner.public_repos.toString()
    }


}
