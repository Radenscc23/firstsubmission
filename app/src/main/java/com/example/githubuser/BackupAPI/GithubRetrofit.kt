package com.example.githubuser.BackupAPI
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


object GithubRetrofit {
    private const val ENDPOINT = "https://api.github.com/"

    val githubRetro = Retrofit.Builder()
        .baseUrl(ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val fetch_api = githubRetro.create(GithubAPI::class.java)
}