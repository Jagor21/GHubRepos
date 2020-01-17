package com.jagor.ghubrepos.gitHub

import com.jagor.ghubrepos.model.Owner
import com.jagor.ghubrepos.model.Repo
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GitHUbService {

    private val BASE_URL = "https://api.github.com"
    private val api: GitHubApi

    init{
        api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(GitHubApi::class.java)
    }

    fun getReposByUser(user: String): Single<List<Repo>> = api.getReposByUser(user)
    fun getUser(user: String): Single<Owner> = api.getUser(user)

}