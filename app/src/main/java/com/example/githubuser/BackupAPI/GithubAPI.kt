package com.example.githubuser.BackupAPI
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.Call


interface GithubAPI {
        @GET("search/users")
        @Headers("Authorization: token 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")
        fun getSearchUsers(
            @Query("q") query: String
        ): Call<ClickUser>

        @GET("users/{username}")
        @Headers("Authorization: token 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")
        fun getUserDetail(
            @Path("username") username: String
        ): Call<DetailClick>

        @GET("users/{username}/followers")
        @Headers("Authorization: token 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")
        fun getFollowers(
            @Path("username") username: String
        ): Call<ArrayList<InfoUser>>

        @GET("users/{username}/following")
        @Headers("Authorization: token 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")
        fun getFollowing(
            @Path("username") username: String
        ): Call<ArrayList<InfoUser>>
}