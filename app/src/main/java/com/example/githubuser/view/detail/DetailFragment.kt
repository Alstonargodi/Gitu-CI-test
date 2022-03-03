package com.example.githubuser.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.githubuser.R
import com.example.githubuser.databinding.FragmentDetailBinding
import com.example.githubuser.view.detail.adapter.SectionPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator


class DetailFragment : Fragment() {
    companion object{
        private var tab_titles = intArrayOf(
            R.string.tabsatu,
            R.string.tabdua,
            R.string.tabtiga
        )
    }

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        setDetailUser()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnBackhome.setOnClickListener {
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToHomeFragment())
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setDetailUser(){
        val data = DetailFragmentArgs.fromBundle(arguments as Bundle).dataDetail

        binding.apply {
            data.apply {
                UsernameTvdetailpage.text = login
                surnameDetailopage.text = name
                BioTvitem.text = bio
                ComapnyTvitem.text = company
                locTvitem.text = location
                FollowerTvitem.text = followers.toString()
                FollowingTvitem.text = following.toString()
                RepositoryTvitem.text = publicRepos.toString()
                webTvitem.text = blog

                setViewPager(login!!)
            }
        }

        Glide.with(requireContext())
            .asDrawable()
            .load(data.avatarUrl)
            .circleCrop()
            .into(binding.ImgTvdetail)
    }

    private fun setViewPager(name : String){
        val pagerAdapter = SectionPagerAdapter(requireActivity(),name,tab_titles.size)
        val viewpager = binding.Followviewpager
        val tabs = binding.tabLayout
        viewpager.adapter = pagerAdapter
        TabLayoutMediator(tabs,viewpager){ tab, position ->
            tab.text = getString(tab_titles[position])
        }.attach()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}