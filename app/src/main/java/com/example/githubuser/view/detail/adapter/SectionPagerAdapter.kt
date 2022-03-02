package com.example.githubuser.view.detail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.githubuser.view.detail.tab.FollowerFragment
import com.example.githubuser.view.detail.tab.FollowingFragment
import com.example.githubuser.view.detail.tab.RepoFragment

class SectionPagerAdapter(activity : FragmentActivity,var value : String,var tab : Int): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return tab
    }

    override fun createFragment(position: Int): Fragment {
    return when(position){
        0 -> RepoFragment(value)
        1 -> FollowerFragment(value)
        2 -> FollowingFragment(value)
        else -> Fragment()
    }
    }

//    return when(position){
//        0 -> FollowerFragment()
//        1 -> FollowerFragment()
//        else -> Fragment()
//    }

//    val tabFragment = FollowerFragment()
//    tabFragment.arguments = Bundle().apply {
//        putInt(FollowerFragment.section_number,position+1)
//        putString("tag",value)
//    }
//    return  tabFragment
}