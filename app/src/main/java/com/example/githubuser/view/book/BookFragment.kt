package com.example.githubuser.view.book

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.githubuser.R
import com.example.githubuser.databinding.FragmentBookBinding
import com.example.githubuser.view.book.adapter.SectionFavoritePagerAdapter
import com.example.githubuser.viewmodel.DetailViewModel
import com.google.android.material.tabs.TabLayoutMediator


class BookFragment : Fragment() {
    private lateinit var binding : FragmentBookBinding
    private val viewModel by viewModels<DetailViewModel>()
    private lateinit var pagerAdapter : SectionFavoritePagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookBinding.inflate(layoutInflater)
        viewModel.getUserDetail(author_name)
        setViewPager()
        setAuthorDetail()
        return binding.root
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
    private fun setAuthorDetail(){
        binding.apply {
            viewModel.apply {
                isLoading.observe(viewLifecycleOwner){ isLoading(it) }
                detailResponse.observe(viewLifecycleOwner){ responData ->
                    UsernameAuthorTv.text = responData.login
                    SurnameAuthorTv.text = responData.name

                    Glide.with(requireContext())
                        .asBitmap()
                        .circleCrop()
                        .load(responData.avatarUrl)
                        .into(imgAuthor)

                    imgAuthor.setOnClickListener {
                        findNavController().navigate(BookFragmentDirections.actionAuthorFragmentToDetailFragment(responData.login!!))
                    }
                }
            }
        }
    }

    private fun isLoading(isLoading:Boolean){
        binding.progressBarAuthor.apply {
            visibility = if (isLoading) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    companion object{
        private const val author_name = "Alstonargodi"
        var tab_titles = intArrayOf(
            R.string.tabfavsatu,
            R.string.tabfavdua
        )
    }


}