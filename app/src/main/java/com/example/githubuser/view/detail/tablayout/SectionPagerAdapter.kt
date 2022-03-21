package com.example.githubuser.view.detail.tablayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.githubuser.view.detail.follower.FollowerFragment
import com.example.githubuser.view.detail.following.FollowingFragment
import com.example.githubuser.view.detail.repository.RepositoryFragment

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