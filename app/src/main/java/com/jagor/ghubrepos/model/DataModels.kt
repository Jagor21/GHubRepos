package com.jagor.ghubrepos.model

import com.google.gson.annotations.SerializedName

data class Repo(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("full_Name")
    val fullName: String,
    @SerializedName("private")
    val private: Boolean,
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("language")
    val language: String
)

data class Owner(
    @SerializedName("login")
    val login: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("public_repos")
    val public_repos: Int
)