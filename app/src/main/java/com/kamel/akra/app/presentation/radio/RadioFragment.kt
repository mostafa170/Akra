package com.kamel.akra.app.presentation.radio

import android.content.ContextWrapper
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kamel.akra.app.presentation.main.MainActivityEventsListener
import com.kamel.akra.databinding.FragmentRadioBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RadioFragment : Fragment() {

    private val viewModel: RadioViewModel by viewModels()
    private var currentPosition: Int = 0

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
        val binding = FragmentRadioBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.currentPosition = currentPosition
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

        viewModel.next.observe(viewLifecycleOwner){
            if (it != null && it){
                val canNext = viewModel.radioChannels.value?.run { currentPosition < this.size -1 }
                    ?: false
                if (canNext){
                    currentPosition += 1
                    binding.currentPosition = currentPosition
                }
                viewModel.onNextDone()
            }
        }

        viewModel.previous.observe(viewLifecycleOwner){
            if (it != null && it){
                if (currentPosition > 0) {
                currentPosition -= 1
                binding.currentPosition = currentPosition
                }
                viewModel.onPreviousDone()
            }
        }

        viewModel.play.observe(viewLifecycleOwner){
            if (it != null && it)
                Log.e("TAG", "onCreateView: $it" )
        }

        return binding.root
    }
}