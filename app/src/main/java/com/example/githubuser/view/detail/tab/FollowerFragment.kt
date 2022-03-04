package com.example.githubuser.view.detail.tab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.databinding.FragmentFollowerBinding
import com.example.githubuser.model.githubresponse.follower.FollowerResponseItem
import com.example.githubuser.view.detail.adapter.FollowerRecviewAdapter
import com.example.githubuser.viewmodel.MainViewModel


class FollowerFragment(var name : String) : Fragment() {
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter : FollowerRecviewAdapter
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowerBinding.inflate(layoutInflater)

        setFollowersList(name)

        return binding.root
    }

    private fun setFollowersList(name : String){
        viewModel.apply {
            getListFollowers(name)
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
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}