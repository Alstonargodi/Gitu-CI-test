package com.example.githubuser.view.author

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.githubuser.databinding.FragmentAuthorBinding
import com.example.githubuser.viewmodel.DetailViewModel


class AuthorFragment : Fragment() {
    private lateinit var binding : FragmentAuthorBinding

    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthorBinding.inflate(layoutInflater)
        viewModel.getUserDetail(author_name)

        setAuthorDetail()
        return binding.root
    }

    private fun setAuthorDetail(){
        binding.apply {
            viewModel.apply {
                isLoading.observe(viewLifecycleOwner){ isLoading(it) }
                detailResponse.observe(viewLifecycleOwner){ responData ->
                    UsernameAuthorTv.text = responData.login
                    SurnameAuthorTv.text = responData.name

                    Glide.with(requireContext())
                        .asBitmap()
                        .circleCrop()
                        .load(responData.avatarUrl)
                        .into(imgAuthor)

                    imgAuthor.setOnClickListener {
                        findNavController().navigate(AuthorFragmentDirections.actionAuthorFragmentToDetailFragment(responData.login!!))
                    }
                }
            }
        }
    }

    private fun isLoading(isLoading:Boolean){
        binding.progressBarAuthor.apply {
            visibility = if (isLoading) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    companion object{
        private const val author_name = "Alstonargodi"
    }
}