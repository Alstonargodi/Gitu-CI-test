package com.example.githubuser.view.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.githubuser.R
import com.example.githubuser.databinding.FragmentDetailBinding
import com.example.githubuser.view.detail.tab.SectionPagerAdapter
import com.example.githubuser.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator


class DetailFragment : Fragment() {
    companion object{
        var tab_titles = intArrayOf(
            R.string.tabsatu,
            R.string.tabdua,
            R.string.tabtiga
        )
        var userNameKey = "username"
    }

    private lateinit var pagerAdapter : SectionPagerAdapter
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<MainViewModel>()

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
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToMainActivity())
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setDetailUser(){
        val userName = DetailFragmentArgs.fromBundle(arguments as Bundle).userName
        viewModel.apply {
            getUserRepo(userName)
            getUserDetail(userName)
            detailResponse.observe(viewLifecycleOwner){ responData ->
                binding.apply {
                    responData.apply {
                        UsernameTvdetailpage.text = login
                        surnameDetailopage.text = name
                        BioTvitem.text = bio
                        ComapnyTvitem.text = company
                        locTvitem.text = location
                        FollowerTvitem.text = followers.toString()
                        FollowingTvitem.text = following.toString()
                        RepositoryTvitem.text = publicRepos.toString()
                        webTvitem.text = blog

                        webTvitem.setOnClickListener {
                            val intent = Intent(Intent.ACTION_VIEW)
                            intent.data = Uri.parse(blog)
                            startActivity(intent)
                        }

                        Glide.with(requireContext())
                            .asDrawable()
                            .load(avatarUrl)
                            .circleCrop()
                            .into(binding.ImgTvdetail)

                        setViewPager(login!!)
                    }
                }
            }
        }

    }

    private fun setViewPager(name : String){
        pagerAdapter = SectionPagerAdapter(requireActivity(),name,tab_titles.size)
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