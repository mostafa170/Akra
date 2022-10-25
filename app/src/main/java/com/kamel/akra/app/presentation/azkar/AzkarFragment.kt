package com.kamel.akra.app.presentation.azkar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kamel.akra.app.presentation.home.HomeFragmentDirections
import com.kamel.akra.databinding.FragmentAzkarBinding
import com.kamel.akra.domain.entities.AzkarNavigationItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AzkarFragment : Fragment() {

    private val viewModel: AzkarViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentAzkarBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.executePendingBindings()

        viewModel.back.observe(viewLifecycleOwner){
            if (it !=null && it){
                findNavController().navigateUp()
                viewModel.onBackNavigated()
            }
        }

        viewModel.goToScreen.observe(viewLifecycleOwner) {
            if (it != null) {
                when (it) {
                    1 -> findNavController().navigate(AzkarFragmentDirections.actionToMyAzkarFragment())
                    2 -> findNavController().navigate(AzkarFragmentDirections.actionToAzkarCategoryList(0))
                    3 -> findNavController().navigate(AzkarFragmentDirections.actionToAzkarCategoryList(1))
                    4 -> findNavController().navigate(AzkarFragmentDirections.actionToAzkarCategoryList(2))
                    5 -> findNavController().navigate(AzkarFragmentDirections.actionToAzkarCategoryList(3))
                    6 -> findNavController().navigate(AzkarFragmentDirections.actionToAzkarCategoryList(4))
                    7 -> findNavController().navigate(AzkarFragmentDirections.actionToAzkarCategoryList(5))
                    8 -> findNavController().navigate(AzkarFragmentDirections.actionToAzkarCategoryList(6))
                    9 -> findNavController().navigate(AzkarFragmentDirections.actionToAzkarCategoryList(7))
                }
                viewModel.restScreen()
            }

        }


        return binding.root

    }


}