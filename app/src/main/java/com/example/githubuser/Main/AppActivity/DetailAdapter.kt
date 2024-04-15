@file:Suppress("DEPRECATION")

package com.example.githubuser.Main.AppActivity

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.githubuser.R
import androidx.annotation.StringRes


class DetailAdapter(private val contextDetail: Context, fm: FragmentManager, data: Bundle) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var fragments: Bundle
    init {
        fragments = data
    }
    @StringRes
    private val FRAGMENTS = intArrayOf(R.string.fragment_one, R.string.fragment_two)

    override fun getCount(): Int = 2
    override fun getPageTitle(position: Int): CharSequence? {
        return contextDetail.resources.getString(FRAGMENTS[position])
    }
    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = fragmentFollowers()
            1 -> fragment = fragmentFollowing()
        }
        fragment?.arguments = this.fragments
        return fragment as Fragment
    }



}