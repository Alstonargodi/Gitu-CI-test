package com.example.githubuser.view.home

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.R
import com.example.githubuser.databinding.FragmentHomeBinding
import com.example.githubuser.model.githubresponse.DetailResponse
import com.example.githubuser.view.home.adapter.UserListRecAdapter
import com.example.githubuser.viewmodel.MainViewModel


class HomeFragment : Fragment() {
    private lateinit var adapter : UserListRecAdapter
    private lateinit var binding: FragmentHomeBinding

    private val viewModel by viewModels<MainViewModel>()

    private var data = ArrayList<DetailResponse>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        setHasOptionsMenu(true)

        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.maincolor)

        adapter = UserListRecAdapter()
        val recView = binding.RecviewUser
        recView.layoutManager = LinearLayoutManager(requireContext())
        recView.adapter = adapter

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar2)
        binding.toolbar2.inflateMenu(R.menu.barmenu)

        viewModel.isLoading.observe(viewLifecycleOwner){
            isloading(it)
        }


        findUser("Alstonargodi")

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.barmenu, menu)

        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
            queryHint = "Input Username"

            setOnSearchClickListener {
                data.clear()
                binding.tvtextsearch.visibility = View.VISIBLE
            }

            setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    binding.textView.text = query
                    findUser(query!!)
                    clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
    }


    private fun findUser(name : String){
        viewModel.apply {
            getListUser(name)
            listresponse.observe(viewLifecycleOwner){ listrespon ->
                for (i in listrespon.indices){
                    getUserDetailList(listrespon[i].login)
                }
            }
        }
    }

    private fun getUserDetailList(username : String){
        viewModel.apply {
            getUserDetail(username)
            detailResponse.observe(viewLifecycleOwner){ detailrespon ->
                data.add(detailrespon)
                adapter.setdata(data)
            }
        }
    }


    private fun isloading(isLoading:Boolean){
        binding.Homeprogress.apply {
            visibility = if (isLoading) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }
}