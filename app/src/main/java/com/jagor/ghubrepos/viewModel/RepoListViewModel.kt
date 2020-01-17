package com.jagor.ghubrepos.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jagor.ghubrepos.gitHub.GitHUbService
import com.jagor.ghubrepos.model.Owner
import com.jagor.ghubrepos.model.Repo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class RepoListViewModel : ViewModel() {

    private val githubService = GitHUbService()
    private val disposable = CompositeDisposable()


    val repos = MutableLiveData<List<Repo>>()
    val repoLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    val owner = MutableLiveData<Owner>()

    fun getReposByUser(user: String) {
        fetchRepos(user)
    }

    private fun fetchRepos(user: String) {
        loading.value = true
        disposable.add(
            githubService.getReposByUser(user)
                .subscribeOn(Schedulers.newThread())
                .subscribeWith(object : DisposableSingleObserver<List<Repo>>() {
                    override fun onSuccess(value: List<Repo>) {
                        repos.postValue(value)
                        repoLoadError.postValue(false)
                        loading.postValue(false)
                    }

                    override fun onError(e: Throwable) {
                        repoLoadError.postValue(true)
                        loading.postValue(false)
                    }
                })
        )

        disposable.add(
            githubService.getUser(user)
                .subscribeOn(Schedulers.newThread())
                .subscribeWith(object : DisposableSingleObserver<Owner>() {
                    override fun onSuccess(value: Owner) {
                        owner.postValue(value)
                    }

                    override fun onError(e: Throwable) {
                        repoLoadError.postValue(true)
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}