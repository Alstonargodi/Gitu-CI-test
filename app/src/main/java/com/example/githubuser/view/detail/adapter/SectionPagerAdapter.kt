package com.example.githubuser.view.detail.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.githubuser.R
import com.example.githubuser.view.detail.DetailFragment
import com.example.githubuser.view.detail.tab.FollowerFragment
import com.example.githubuser.view.detail.tab.FollowerFragmentArgs
import com.example.githubuser.view.detail.tab.FollowingFragment
import com.example.githubuser.view.detail.tab.RepositoryFragment

class SectionPagerAdapter(activity : FragmentActivity, private var value : String, private var tab : Int): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return tab
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                RepositoryFragment().apply {
                    arguments = Bundle().apply { putString("value",value) }
                }
            }
            1 -> {
                FollowerFragment().apply {
                    arguments = Bundle().apply { putString("value",value) }
                }
            }
            2 ->{
                FollowingFragment().apply {
                    arguments = Bundle().apply { putString("value",value) }
                }
            }
            else -> Fragment()
        }

    }
}