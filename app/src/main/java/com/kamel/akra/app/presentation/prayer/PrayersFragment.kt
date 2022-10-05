package com.kamel.akra.app.presentation.prayer

import android.content.ContextWrapper
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kamel.akra.app.presentation.main.MainActivityEventsListener
import com.kamel.akra.databinding.FragmentPrayersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PrayersFragment : Fragment() {

    private val viewModel: PrayersViewModel by viewModels()

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
        val binding = FragmentPrayersBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.executePendingBindings()

        viewModel.back.observe(viewLifecycleOwner) {
            if (it != null && it) {
                this.findNavController().navigateUp()
                viewModel.onBackNavigated()
            }
        }
        viewModel.error.observe(viewLifecycleOwner) {
            if (it != null) {
                mainActivityEventsListener.showErrorMessage(it)
                viewModel.onErrorMessageShown()
            }
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it != null && it)
                mainActivityEventsListener.showLoading()
            else
                mainActivityEventsListener.hideLoading()
        }


        return binding.root
    }


}