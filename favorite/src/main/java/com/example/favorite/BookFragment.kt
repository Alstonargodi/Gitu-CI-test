package com.example.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.core.di.BookModuleDepedencies
import com.example.core.di.FavoriteModuleDependecies
import com.example.favorite.adapter.FavoriteRepositoryRecyclerViewAdapter
import com.example.favorite.adapter.FavoriteUserRecyclerViewAdapter
import com.example.favorite.adapter.SectionFavoritePagerAdapter
import com.example.favorite.component.DaggerBookComponent
import com.example.favorite.component.DaggerFavoriteComponent
import com.example.favorite.databinding.FragmentBookBinding
import com.example.favorite.databinding.FragmentFavoriteBinding
import com.example.favorite.viewmodel.FavoriteViewModel
import com.example.favorite.viewmodel.ViewModelFactory
import com.example.githubuser.R
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject


class BookFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val favoriteViewModel : FavoriteViewModel by viewModels{
        viewModelFactory
    }

    private lateinit var binding : FragmentBookBinding
    private lateinit var pagerAdapter : SectionFavoritePagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        DaggerBookComponent.builder()
            .context(requireContext())
            .appDepedencies(
                EntryPointAccessors.fromApplication(
                    requireContext(),
                    BookModuleDepedencies::class.java
                )
            )
            .build()
            .inject(this)
        binding = FragmentBookBinding.inflate(layoutInflater)
        favoriteViewModel.clearDetail()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewPager()
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