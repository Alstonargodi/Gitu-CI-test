package com.example.githubuser.view.detail.follower

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.databinding.FragmentFollowerBinding
import com.example.githubuser.model.githubresponse.follower.FollowerResponseItem
import com.example.githubuser.viewmodel.FollowerViewModel
import com.example.githubuser.viewmodel.UtilViewModel
import com.google.android.material.snackbar.Snackbar


class FollowerFragment: Fragment() {
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter : FollowerRecviewAdapter
    private val followerViewModel by viewModels<FollowerViewModel>()
    private val utilViewModel by viewModels<UtilViewModel>()

    private var userName = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowerBinding.inflate(layoutInflater)

        userName = arguments?.getString("value","").toString()


        followerViewModel.apply {
            isLoading.observe(viewLifecycleOwner){ isLoading(it) }
            getListFollowers(userName)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFollowersList()
        emptyChecker()
    }

    private fun setFollowersList(){
        followerViewModel.apply {
            followerResponse.observe(viewLifecycleOwner){ responData ->
                showFollowerList(responData)
            }
        }
    }

    private fun showFollowerList(list: List<FollowerResponseItem>){
        adapter = FollowerRecviewAdapter(list)
        val recycView = binding.followerrecview
        recycView.adapter = adapter
        recycView.layoutManager = LinearLayoutManager(requireContext())
        utilViewModel.setEmptys(adapter.itemCount)
        emptyChecker()

        if (context?.resources?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE){
            recycView.layoutManager = GridLayoutManager(requireContext(),2)
        }else{
            recycView.layoutManager = LinearLayoutManager(requireContext())
        }
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
            utilViewModel.isEmpty.observe(viewLifecycleOwner){ value ->
                if (value == 0){
                    EmptyStatment.visibility = View.VISIBLE
                    "$userName doesn't have any followers yet".also { EmptyStatment.text = it }
                }
            }
            followerViewModel.errorResponse.observe(viewLifecycleOwner){ response ->
                Log.d("error response",response)
                if (response.isNotEmpty()){
                    Snackbar.make(root, response.toString(), Snackbar.LENGTH_SHORT)
                        .setTextColor(Color.WHITE)
                        .setBackgroundTint(Color.rgb(255, 100, 100))
                        .show()
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


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}