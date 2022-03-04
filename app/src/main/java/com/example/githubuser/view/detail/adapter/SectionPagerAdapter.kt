package com.example.githubuser.view.detail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.githubuser.view.detail.tab.FollowerFragment
import com.example.githubuser.view.detail.tab.FollowingFragment
import com.example.githubuser.view.detail.tab.RepositoryFragment

class SectionPagerAdapter(activity : FragmentActivity, private var value : String, private var tab : Int): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return tab
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> RepositoryFragment(value)
            1 -> FollowerFragment(value)
            2 -> FollowingFragment(value)
            else -> Fragment()
        }
    }
}