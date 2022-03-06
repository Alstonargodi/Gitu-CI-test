package com.example.githubuser.view.home


import android.app.SearchManager
import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.util.Log
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
import com.example.githubuser.model.githubresponse.ItemsItem
import com.example.githubuser.view.home.adapter.UserListRecAdapter
import com.example.githubuser.viewmodel.MainViewModel
import com.example.githubuser.viewmodel.UtilViewModel
import com.google.android.material.snackbar.Snackbar


class HomeFragment : Fragment() {
    companion object{
        private const val default_user = "followers:>1000"
    }

    private lateinit var adapter : UserListRecAdapter
    private lateinit var binding: FragmentHomeBinding

    private val mainViewModel by viewModels<MainViewModel>()
    private val utilViewModel by viewModels<UtilViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        setHasOptionsMenu(true)

        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.maincolor)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar2)
        binding.toolbar2.inflateMenu(R.menu.barmenu)


        mainViewModel.apply {
            isLoading.observe(viewLifecycleOwner){ isLoading(it) }

            listresponse.observe(viewLifecycleOwner){ listRespon ->
                showUserList(listRespon)
            }
        }
        utilViewModel.apply {
            searchQuery.observe(viewLifecycleOwner){ text ->
                binding.CurrsearchTv.text = "search result for $text"
            }
        }


        showErrorText()
        setEmpty()

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
                    setSearch(query!!)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })

        }
    }

    private fun setSearch(search : String){
        mainViewModel.apply {
            getListUser(search)
            binding.tvEmptyview.visibility = View.GONE
        }
        utilViewModel.saveSearch(search)
        binding.CurrsearchTv.text = "search result for $search"

        when(search){
            default_user->binding.CurrsearchTv.text = "Top Users"
        }
    }



    private fun showUserList(list: List<ItemsItem>){
        adapter = UserListRecAdapter(list)
        val recView = binding.RecviewUser
        recView.adapter = adapter
        utilViewModel.setEmptys(adapter.itemCount)
        Log.d("item",adapter.itemCount.toString())

        if (context?.resources?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE){
            recView.layoutManager = GridLayoutManager(requireContext(),2)
        }else{
            recView.layoutManager = LinearLayoutManager(requireContext())
        }

    }


    private fun showErrorText(){
        binding.apply {
            mainViewModel.errorResponseText.observe(viewLifecycleOwner){ errorResponse ->
                if (errorResponse.isNotEmpty()){
                    Snackbar.make(root, errorResponse.toString(), Snackbar.LENGTH_SHORT)
                            .setTextColor(Color.WHITE)
                            .setBackgroundTint(Color.rgb(255, 100, 100))
                            .show()
                        ErrortextTv.visibility = View.VISIBLE
                        TryagainHome.visibility = View.VISIBLE
                        tvEmptyview.visibility = View.GONE
                        RecviewUser.visibility = View.GONE
                        ErrortextTv.text = errorResponse.toString()
                        TryagainHome.setOnClickListener {
                            utilViewModel.searchQuery.observe(viewLifecycleOwner){  text ->
                                mainViewModel.getListUser(text)
                                binding.CurrsearchTv.text = "search result for $text"
                            }
                            ErrortextTv.visibility = View.GONE
                            TryagainHome.visibility = View.GONE
                            tvEmptyview.visibility = View.GONE
                            RecviewUser.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }

    private fun isLoading(isLoading:Boolean){
        binding.Homeprogress.visibility = if (isLoading)  View.VISIBLE  else  View.GONE
    }

    private fun setEmpty(){
        utilViewModel.isEmpty.observe(viewLifecycleOwner){
            if (it == 0){
                setSearch(default_user) // Top User when search empty
                binding.tvEmptyview.visibility = View.GONE
            }else if (it != 0){
                binding.tvEmptyview.visibility = View.GONE
            }
        }
    }
}