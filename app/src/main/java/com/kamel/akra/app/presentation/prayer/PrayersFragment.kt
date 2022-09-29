package com.kamel.akra.app.presentation.prayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kamel.akra.R
import com.kamel.akra.databinding.FragmentHomeBinding
import com.kamel.akra.databinding.FragmentPrayersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PrayersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentPrayersBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        //binding.viewModel = viewModel
        binding.executePendingBindings()

        return binding.root
    }


}