package com.example.githubuser.view.detail.follower

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.databinding.FragmentFollowerBinding
import com.example.githubuser.data.remote.githubresponse.follower.FollowerResponseItem
import com.example.githubuser.view.detail.DetailFragmentDirections
import com.example.githubuser.viewmodel.FollowerViewModel
import com.example.githubuser.viewmodel.util.UtilViewModel


class FollowerFragment: Fragment() {
    private lateinit var binding: FragmentFollowerBinding

    private lateinit var adapter : FollowerRecviewAdapter
    private val followerViewModel by viewModels<FollowerViewModel>()
    private val utilViewModel by viewModels<UtilViewModel>()

    private var userName = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowerBinding.inflate(layoutInflater)
        userName = arguments?.getString("value","").toString()

        utilViewModel.apply {
            textQuery.observe(viewLifecycleOwner){
                userName = it
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFollowersList()
        userChecker()
    }

    private fun setFollowersList(){
        followerViewModel.apply {
            getListFollowers(userName)
            followerResponse.observe(viewLifecycleOwner){ responData ->
                isLoading.observe(viewLifecycleOwner){ isLoading(it) }
                showFollowerList(responData)
                utilViewModel.apply {
                    saveText(userName)
                }
            }
        }
    }

    private fun userChecker(){
        utilViewModel.isEmpty.observe(viewLifecycleOwner) { isDataNotExist ->
            if (isDataNotExist == true) {
                setErrorView()
            }
        }
    }

    private fun showFollowerList(list: List<FollowerResponseItem>){
        adapter = FollowerRecviewAdapter(list)
        val recycView = binding.followerrecview
        recycView.adapter = adapter
        recycView.layoutManager = LinearLayoutManager(requireContext())
        utilViewModel.apply { if (adapter.itemCount== 0) setEmptys(true) else setEmptys(false) }
        emptyChecker()

        if (context?.resources?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE){
            recycView.layoutManager = GridLayoutManager(requireContext(),2)
        }else{
            recycView.layoutManager = LinearLayoutManager(requireContext())
        }

        adapter.onItemClickDetail(object : FollowerRecviewAdapter.onItemCallDetail{
            override fun onItemCallDetail(username: String) {
                findNavController().navigate(DetailFragmentDirections.actionDetailFragmentSelf(username))
            }
        })

    }

    private fun isLoading(isLoading:Boolean){
        binding.FollowerProgress.apply {
            visibility = if (isLoading) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }


    private fun emptyChecker(){
        binding.apply {
            utilViewModel.isEmpty.observe(viewLifecycleOwner){ isDataNotExist ->
                if (isDataNotExist == true){
                    EmptyStatment.visibility = View.VISIBLE
                    "$userName doesn't have any followers yet".also { EmptyStatment.text = it }
                }
            }
        }
    }

    private fun setErrorView(){
        binding.apply {
            followerViewModel.errorResponse.observe(viewLifecycleOwner){ response ->
                if (response.isNotEmpty()){
                    EmptyStatment.visibility = View.VISIBLE
                    ("$response\n \n Try Again").also { EmptyStatment.text = it }
                    EmptyStatment.setOnClickListener {
                        followerViewModel.getListFollowers(userName)
                        EmptyStatment.visibility = View.GONE
                    }
                }
            }
        }
    }



}