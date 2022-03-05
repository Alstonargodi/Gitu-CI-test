package com.example.githubuser.view.home

import android.app.SearchManager
import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.R
import com.example.githubuser.databinding.FragmentHomeBinding
import com.example.githubuser.model.githubresponse.DetailResponse
import com.example.githubuser.model.githubresponse.ItemsItem
import com.example.githubuser.model.githubresponse.ListResponse
import com.example.githubuser.view.home.adapter.UserListRecAdapter
import com.example.githubuser.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar


class HomeFragment : Fragment() {
    private lateinit var adapter : UserListRecAdapter
    private lateinit var binding: FragmentHomeBinding

    private val viewModel by viewModels<MainViewModel>()


    private var data  = mutableListOf<DetailResponse>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        setHasOptionsMenu(true)

        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.maincolor)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar2)
        binding.toolbar2.inflateMenu(R.menu.barmenu)

        viewModel.isLoading.observe(viewLifecycleOwner){ isLoading(it) }


        viewModel.apply {
            listresponse.observe(viewLifecycleOwner){ listrespon ->
                showUserList(listrespon)
            }

        }

        showErrorText()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.barmenu, menu)

        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        val authorView = menu.findItem(R.id.author)


        authorView.apply {
            setOnMenuItemClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAuthorFragment())
                true
            }
        }

        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
            queryHint = "input username"

            setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {

                    viewModel.getListUser(query!!)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })

        }
    }


    private fun getUserList(){
    }


    private fun showUserList(list: List<ItemsItem>){
        adapter = UserListRecAdapter(list)
        val recView = binding.RecviewUser
        recView.adapter = adapter


        if (context?.resources?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE){
            recView.layoutManager = GridLayoutManager(requireContext(),2)
        }else{
            recView.layoutManager = LinearLayoutManager(requireContext())
        }

    }


    private fun showErrorText(){
        binding.apply {
            viewModel.errorResponseText.observe(viewLifecycleOwner){ errorResponse ->
                Snackbar.make(root, errorResponse.toString(), Snackbar.LENGTH_SHORT)
                    .setTextColor(Color.WHITE)
                    .setBackgroundTint(Color.rgb(255, 100, 100))
                    .show()
                ErrortextTv.visibility = View.VISIBLE
                TryagainHome.visibility = View.VISIBLE
                ErrortextTv.text = errorResponse.toString()
                TryagainHome.setOnClickListener {
                    getUserList()
                    ErrortextTv.visibility = View.GONE
                    TryagainHome.visibility = View.GONE
                }
            }
        }
    }

    private fun isLoading(isLoading:Boolean){
        binding.apply {
            Homeprogress.visibility = if (isLoading)  View.VISIBLE  else  View.GONE
        }
    }
}