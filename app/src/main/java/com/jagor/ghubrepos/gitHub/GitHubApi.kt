package com.jagor.ghubrepos.gitHub

import com.jagor.ghubrepos.model.Owner
import com.jagor.ghubrepos.model.Repo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {

    @GET("/users/{user}/repos")
    fun getReposByUser(@Path("user") user: String): Single<List<Repo>>

    @GET("/users/{user}")
    fun getUser(@Path("user") user: String): Single<Owner>

}