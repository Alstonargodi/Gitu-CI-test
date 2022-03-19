package com.example.githubuser.view.author.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.githubuser.view.author.favoritetab.FavoriteFragment

class SectionFavoritePagerAdapter(activity : FragmentActivity,private var tab : Int): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = tab

    override fun createFragment(position: Int): Fragment {
        val fragment = FavoriteFragment()
        fragment.arguments = Bundle().apply {
            putInt("number",position + 1)
        }
        return fragment
    }

}