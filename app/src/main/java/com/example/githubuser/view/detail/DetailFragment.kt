package com.example.githubuser.view.detail

import android.content.Intent
import android.graphics.Color
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
import com.example.githubuser.viewmodel.DetailViewModel
import com.example.githubuser.viewmodel.UtilViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator


class DetailFragment : Fragment() {
    companion object{
        var tab_titles = intArrayOf(
            R.string.tabsatu,
            R.string.tabdua,
            R.string.tabtiga
        )
    }

    private lateinit var pagerAdapter : SectionPagerAdapter
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val detailViewModel by viewModels<DetailViewModel>()
    private val utilViewModel by viewModels<UtilViewModel>()


    private var saveText = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(layoutInflater)

        utilViewModel.textQuery.observe(viewLifecycleOwner){ saveText = it }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBackhome.setOnClickListener {
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToMainActivity())
        }
        setDetailUser()
    }

    private fun setDetailUser(){
        val userName = DetailFragmentArgs.fromBundle(arguments as Bundle).userName
        utilViewModel.saveText(userName)
        detailViewModel.apply {
            getUserDetail(userName)
            isLoading.observe(viewLifecycleOwner){ isLoading(it) }
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



                        if(bio.isNullOrEmpty()) BioTvitem.visibility = View.GONE
                        if(company.isNullOrEmpty()) ComapnyTvitem.visibility = View.GONE
                        if(location.isNullOrEmpty()) locTvitem.visibility = View.GONE
                        if(blog.isNullOrEmpty()) webTvitem.visibility = View.GONE


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
            showErrorText()
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


    private fun showErrorText(){
        binding.apply {
            detailViewModel.errorResponse.observe(viewLifecycleOwner){ response->
                if (response.isNotEmpty()){
                    Snackbar.make(root, response.toString(), Snackbar.LENGTH_SHORT)
                        .setTextColor(Color.WHITE)
                        .setBackgroundTint(Color.rgb(255, 100, 100))
                        .show()
                    ErrorresponseDetailTv.visibility = View.VISIBLE
                    ("$response\n \n Try Again").also { ErrorresponseDetailTv.text = it }
                    ErrorresponseDetailTv.setOnClickListener {
                        detailViewModel.getUserDetail(saveText)
                        ErrorresponseDetailTv.visibility = View.GONE
                    }
                }
            }
        }
    }
    private fun isLoading(isLoading:Boolean){
        binding.DetailProgress.visibility = if (isLoading)  View.VISIBLE  else  View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    }
