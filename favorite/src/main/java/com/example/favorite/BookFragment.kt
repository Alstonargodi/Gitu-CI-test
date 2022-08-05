package com.example.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.favorite.adapter.SectionFavoritePagerAdapter
import com.example.favorite.databinding.FragmentBookBinding
import com.example.githubuser.R
import com.google.android.material.tabs.TabLayoutMediator


class BookFragment : Fragment() {
    private lateinit var binding : FragmentBookBinding
    private lateinit var pagerAdapter : SectionFavoritePagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookBinding.inflate(layoutInflater)
        setViewPager()
        return binding.root
    }

    private fun setViewPager(){
        pagerAdapter = SectionFavoritePagerAdapter(
            requireActivity(),
            tab_titles.size
        )
        val viewPager = binding.AuthorPager
        val tabLayout = binding.TablayoutAuthor
        viewPager.adapter = pagerAdapter
        TabLayoutMediator(tabLayout,viewPager){tab,position ->
            tab.text = getString(tab_titles[position])
        }.attach()
    }


    companion object{
        var tab_titles = intArrayOf(
            R.string.tabfavsatu,
            R.string.tabfavdua
        )
    }


}