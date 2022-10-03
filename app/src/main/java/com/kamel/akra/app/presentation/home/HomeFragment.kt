package com.kamel.akra.app.presentation.home

import android.content.ContextWrapper
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kamel.akra.app.presentation.main.MainActivityEventsListener
import com.kamel.akra.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    private val mainActivityEventsListener: MainActivityEventsListener by lazy {
        requireNotNull(context) {
            "Context must not be null"
        }
        (context as ContextWrapper).baseContext as MainActivityEventsListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.executePendingBindings()

        viewModel.goToScreen.observe(viewLifecycleOwner) {
            if (it != null) {
                when (it) {
                    1 -> findNavController().navigate(HomeFragmentDirections.actionToTilawaFragment())
                    2 -> findNavController().navigate(HomeFragmentDirections.actionToQiblaFragment())
                    3 -> findNavController().navigate(HomeFragmentDirections.actionToSebhaFragment())
                    4 -> findNavController().navigate(HomeFragmentDirections.actionToRadioFragment())
                    5 -> findNavController().navigate(HomeFragmentDirections.actionToQuranFragment())
                    6 -> findNavController().navigate(HomeFragmentDirections.actionToAzkarFragment())
                    7 -> findNavController().navigate(HomeFragmentDirections.actionToHadethFragment())
                    8 -> findNavController().navigate(HomeFragmentDirections.actionToPrayersFragment())
                }
                viewModel.restScreen()
            }

        }

        return binding.root
    }
}