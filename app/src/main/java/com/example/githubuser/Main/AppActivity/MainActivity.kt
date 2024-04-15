package com.example.githubuser.Main.AppActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appgithub.ui.main.Settings
import com.example.githubuser.BackupAPI.InfoUser
import com.example.githubuser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var Mainview: Settings
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = Adapter()
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickCallback(object : Adapter.OnItemClickCallback{
            override fun onItemClicked(data: InfoUser) {
                Intent(this@MainActivity, Detail::class.java).also {
                    it.putExtra(Detail.USERNAME, data.login)
                    it.putExtra(Detail.USERID, data.id)
                    startActivity(it)
                }
            }

        })

        Mainview = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(Settings::class.java)

        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = adapter

            btnSearch.setOnClickListener {
                clickUsername()
            }

            etQuery.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    clickUsername()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }

        Mainview.getSearchUsers().observe(this, {
            if (it != null) {
                adapter.setArrayList(it)
                showLoading(false)
            }
        })
    }
    private fun showLoading(state: Boolean){
        if (state){
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        } }
    private fun clickUsername(){
        binding.apply {
            val query = etQuery.text.toString()
            if (query.isEmpty()) return
            showLoading(true)
            Mainview.setSearchUsers(query)
        } }
}