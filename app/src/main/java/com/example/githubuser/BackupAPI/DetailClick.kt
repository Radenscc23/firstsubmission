package com.example.githubuser.BackupAPI

data class DetailClick(val login: String,
                       val id: String,
                       val avatar_url: String,
                       val followers_url: String,
                       val following_url: String,
                       val name: String,
                       val following: Int,
                       val followers: Int)
