package com.example.githubuser.view.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.githubuser.R
import com.example.githubuser.databinding.FragmentDetailBinding
import com.example.githubuser.local.entity.FavoritePeople
import com.example.githubuser.view.detail.tab.SectionPagerAdapter
import com.example.githubuser.viewmodel.DetailViewModel
import com.example.githubuser.viewmodel.FavoriteViewModel
import com.example.githubuser.viewmodel.util.UtilViewModel
import com.example.githubuser.viewmodel.util.obtainViewModel
import com.google.android.material.tabs.TabLayoutMediator


class DetailFragment : Fragment() {
    private val detailViewModel by viewModels<DetailViewModel>()
    private val utilViewModel by viewModels<UtilViewModel>()
    private lateinit var favoriteViewModel : FavoriteViewModel

    private lateinit var pagerAdapter : SectionPagerAdapter
    private lateinit var binding: FragmentDetailBinding
    private var saveText = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        saveText = DetailFragmentArgs.fromBundle(arguments as Bundle).userName

        favoriteViewModel = obtainViewModel(requireActivity())

        utilViewModel.apply {
            textQuery.observe(viewLifecycleOwner){
                saveText = it
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnBackhome.setOnClickListener {
                findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToHomeFragment())
            }

            btnShare.setOnClickListener {
                val share= Intent()
                share.action = Intent.ACTION_SEND
                share.type = "text/plain"
                share.putExtra(Intent.EXTRA_TEXT,"Visit $saveText on github")

                startActivity(Intent.createChooser(share,"Share to"))
            }

            btnFavorite.setOnClickListener {
                setAsFavorite()
            }
        }

        setDetailUser()
        userChecker()
    }

    private fun setDetailUser(){
        detailViewModel.apply {
            getUserDetail(saveText)
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
                utilViewModel.apply {
                    saveText(saveText)
                    setEmptys(false)
                }
            }
        }
    }

    private fun setAsFavorite(){
        detailViewModel.detailResponse.observe(viewLifecycleOwner){ respon->
            respon?.apply {
                login?.let {
                    val favTemp = FavoritePeople(
                        login,
                        name,
                        avatarUrl,
                        publicRepos,
                        followers,
                        following,
                        bio,
                        company,
                        location,
                        blog,
                        true
                    )
                    favoriteViewModel.inserFavoritePeople(favTemp)
                    Toast.makeText(context,"favoritbaru",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun setViewPager(name : String){
        pagerAdapter = SectionPagerAdapter(requireActivity(),name,tab_titles.size)
        val viewPager = binding.Followviewpager
        val tabs = binding.tabLayout
        viewPager.adapter = pagerAdapter
        TabLayoutMediator(tabs,viewPager){ tab, position ->
            tab.text = getString(tab_titles[position])
        }.attach()
    }

    private fun userChecker(){
        utilViewModel.isEmpty.observe(viewLifecycleOwner) { isUserNotExist ->
            if (isUserNotExist == true) {
                setErrorView()
            }
        }
    }


    private fun setErrorView(){
        binding.apply {
            detailViewModel.errorResponse.observe(viewLifecycleOwner){ response->
                if (response.isNotEmpty()){
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


    companion object{
        var tab_titles = intArrayOf(
            R.string.tabsatu,
            R.string.tabdua,
            R.string.tabtiga
        )
    }


}
