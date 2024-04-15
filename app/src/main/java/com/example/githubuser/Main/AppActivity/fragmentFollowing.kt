package com.example.githubuser.Main.AppActivity
import com.example.githubuser.R
import com.example.githubuser.databinding.SumfollowFragmentBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import android.os.Bundle
import android.view.View

class fragmentFollowing: Fragment(R.layout.sumfollow_fragment) {
    private lateinit var pickAdapter: Adapter
    private lateinit var listofUsers: String
    private var _binding : SumfollowFragmentBinding?= null
    private val binding get() = _binding!!
    private lateinit var followingSetting: SettingFollowing
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        pickAdapter = Adapter()
        pickAdapter.notifyDataSetChanged()
        listofUsers = args?.getString(Detail.USERNAME).toString()
        _binding = SumfollowFragmentBinding.bind(view)

        binding.apply {
            rvUser.setHasFixedSize(true)
            rvUser.layoutManager = LinearLayoutManager(activity)
            rvUser.adapter = pickAdapter
        }

        loadingData(true)
        followingSetting = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            SettingFollowing::class.java)
        followingSetting.sumofFollowing(listofUsers)
        followingSetting.getListFollowing().observe(viewLifecycleOwner, {
            if (it != null){
                pickAdapter.setArrayList(it)
                loadingData(false)
            }
        })
    }
    private fun loadingData(state: Boolean){
        if (state){
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}