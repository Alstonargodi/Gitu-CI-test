package com.example.githubuser.presentation.fragment.detail.tabadapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.githubuser.presentation.fragment.follower.FollowerFragment
import com.example.githubuser.presentation.fragment.following.FollowingFragment
import com.example.githubuser.presentation.fragment.repositoryuser.RepositoryFragment

class SectionPagerAdapter(activity : FragmentActivity, private var value : String, private var tab : Int): FragmentStateAdapter(activity) {
    override fun getItemCount() = tab

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