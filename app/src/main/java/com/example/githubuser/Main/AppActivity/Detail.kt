package com.example.githubuser.Main.AppActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.githubuser.databinding.ActivityDetailUserBinding
import android.view.View
import androidx.lifecycle.ViewModelProvider


class Detail : AppCompatActivity() {
    private lateinit var DetailSettings: SettingsDetail
    private lateinit var binding: ActivityDetailUserBinding
    companion object{
        const val USERNAME = "username"
        const val USERID = "userid"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(USERNAME)
        val id = intent.getIntExtra(USERID, 0)
        val bundle = Bundle()
        bundle.putString(USERNAME, username)

        DetailSettings = ViewModelProvider(this).get(SettingsDetail::class.java)

        showLoading(true)
        DetailSettings.userDetails(username.toString())
        DetailSettings.fetchUserinDetails().observe(this, {
            if (it != null){
                binding.apply {
                    tvName.text = it.name
                    tvUsername.text = it.login
                    tvFollowers.text = "${it.followers} Followers"
                    tvFollowing.text = "${it.following} Following"
                    Glide.with(this@Detail)
                        .load(it.avatar_url)
                        .centerCrop()
                        .into(ivProfile)
                    showLoading(false)
                }
            }
        })

        val secondAdapter = DetailAdapter (this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = secondAdapter
            tabs.setupWithViewPager(viewPager)
        }
    }

    private fun showLoading(state: Boolean){
        if (state){
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}