package com.example.githubuser.presentation.fragment.detail

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.githubuser.R
import com.example.githubuser.databinding.FragmentDetailBinding
import com.example.core.data.local.entity.userlist.GithubListUser
import com.example.core.data.remote.utils.Resource
import com.example.core.domain.model.UserDetail
import com.example.core.utils.DataMapper
import com.example.githubuser.presentation.fragment.detail.tabadapter.SectionPagerAdapter
import com.example.githubuser.presentation.utils.UtilViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val detailViewModel : DetailViewModel by viewModels()
    private val utilViewModel by viewModels<UtilViewModel>()

    private lateinit var pagerAdapter : SectionPagerAdapter
    private lateinit var binding: FragmentDetailBinding
    private var saveText = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        try {
            saveText = requireArguments().getString("favorite").toString()
        }catch (e : Exception){
            Log.d("detailFragment",e.toString())
        }


        favoriteChecker()

        utilViewModel.apply {
            textQuery.observe(viewLifecycleOwner){
                saveText = it
            }
        }

        showDetailUser()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnShare.setOnClickListener {
                val share= Intent()
                share.action = Intent.ACTION_SEND
                share.type = "text/plain"
                share.putExtra(Intent.EXTRA_TEXT,"Visit $saveText on github")
                startActivity(Intent.createChooser(share,"Share to"))
            }
        }
        userChecker()
    }

    private fun showDetailUser(){
        detailViewModel.getUserDetail(saveText).observe(viewLifecycleOwner){ response ->
            when(response){
                is Resource.Loading->{
                    binding.DetailProgress.visibility = View.VISIBLE
                }
                is Resource.Success->{
                    response.data?.get(0)?.let { setDetailUser(it) }
                    binding.DetailProgress.visibility = View.GONE
                }
                is Resource.Error->{
                    binding.DetailProgress.visibility = View.GONE
                }
            }
        }
    }
    private fun setDetailUser(responData : UserDetail){
        binding.apply {
            responData.apply {
                UsernameTvdetailpage.text = nickName
                surnameDetailopage.text = userName
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
                setViewPager(userName!!)
            }
        }
        utilViewModel.apply {
            saveText(saveText)
            setEmptyView(false)
        }

        binding.btnFavorite.setOnClickListener {
            setAsFavorite(responData)
        }
    }

    private fun setAsFavorite(respon: UserDetail){
        respon.apply {
            nickName.let {
                val favTemp = DataMapper.userSetFavoriteUser(respon)
                detailViewModel.insertFavoritePeople(favTemp)
                Snackbar.make(binding.root,"add $it as Favorite People",
                    Snackbar.LENGTH_LONG)
                    .setTextColor(Color.WHITE)
                    .setBackgroundTint(Color.rgb(0, 200, 151))
                    .show()
            }
        }
    }

    private fun removeFromFavorite(){
        detailViewModel.deletePersonFavoritePeople(saveText)
        Snackbar.make(binding.root,"Remove $saveText ",
            Snackbar.LENGTH_LONG)
            .setTextColor(Color.WHITE)
            .setBackgroundTint(Color.rgb(137, 15, 13))
            .show()
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

    private fun favoriteChecker(){
        detailViewModel.searchFavoritePeople(saveText).observe(viewLifecycleOwner){
            try {
                if (saveText == it[0].name){
                    binding.btnFavorite.apply {
                        setImageDrawable(
                            ContextCompat.getDrawable(requireContext(),R.drawable.ic_baseline_favorite_full)
                        )
                        setOnClickListener {
                            removeFromFavorite()
                        }
                    }
                }
            }catch (e : Exception){
                binding.btnFavorite.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(),R.drawable.ic_baseline_favorite_border_24)
                )
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
