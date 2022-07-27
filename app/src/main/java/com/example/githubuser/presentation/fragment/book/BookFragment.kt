package com.example.githubuser.presentation.fragment.book

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.githubuser.R
import com.example.githubuser.databinding.FragmentBookBinding
import com.example.githubuser.myapplication.MyApplication
import com.example.githubuser.presentation.fragment.book.adapter.SectionFavoritePagerAdapter
import com.example.githubuser.presentation.fragment.detail.DetailViewModel
import com.example.githubuser.presentation.utils.viewmodelfactory.ViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

class BookFragment : Fragment() {
    @Inject
    lateinit var factory : ViewModelFactory

    private lateinit var binding : FragmentBookBinding

    private val viewModel : DetailViewModel by viewModels{
        factory
    }

    private lateinit var pagerAdapter : SectionFavoritePagerAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookBinding.inflate(layoutInflater)
        viewModel.getUserDetail(author_name)
        setViewPager()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAuthor.setOnClickListener {
            findNavController().navigate(
                BookFragmentDirections.actionAuthorFragmentToDetailFragment(
                    author_name
                )
            )
        }
    }
    private fun setViewPager(){
        pagerAdapter = SectionFavoritePagerAdapter(requireActivity(), tab_titles.size)
        val viewPager = binding.AuthorPager
        val tabLayout = binding.TablayoutAuthor
        viewPager.adapter = pagerAdapter
        TabLayoutMediator(tabLayout,viewPager){tab,position ->
            tab.text = getString(tab_titles[position])
        }.attach()
    }


    companion object{
        private const val author_name = "Alstonargodi"
        var tab_titles = intArrayOf(
            R.string.tabfavsatu,
            R.string.tabfavdua
        )
    }


}