package com.jagor.ghubrepos.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jagor.ghubrepos.model.Owner
import com.jagor.ghubrepos.model.Repo

class RepoListViewModel: ViewModel() {

    val repos = MutableLiveData<List<Repo>>()
    val repoLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    val owner = MutableLiveData<Owner>()

    fun getReposByUser(user: String) {
        fetchRepos(user)
    }

    private fun fetchRepos(user: String) {
        
    }
}