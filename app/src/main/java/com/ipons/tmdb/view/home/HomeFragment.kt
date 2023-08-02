package com.ipons.tmdb.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ipons.domain.model.ContainerBO
import com.ipons.tmdb.databinding.HomeFragmentBinding
import com.ipons.tmdb.extensions.launchAndCollect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.getHome()
        initStatusListeners()
    }

    private fun initStatusListeners() {
        viewLifecycleOwner.launchAndCollect(homeViewModel.status) { status ->
            when (status) {
                is HomeStatus.ShowHome -> setAdapter(status.home)
            }
        }
    }

    private fun setAdapter(home: List<ContainerBO>) {
        binding.rvHome.adapter = HomeAdapter(home)
    }

}