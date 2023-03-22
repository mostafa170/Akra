package com.kamel.akra.app.presentation.tilawa

import android.content.ContextWrapper
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kamel.akra.app.presentation.main.MainActivityEventsListener
import com.kamel.akra.databinding.FragmentTilawaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TilawaFragment : Fragment() {

    private val viewModel: TilawaViewModel by viewModels()

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
        val binding = FragmentTilawaBinding.inflate(inflater)
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

        val onItemClickListener = OnSurahAudioClickListener{
            if (it !=null){
                Log.e("TAG", "onItemClickListener: $it" )
            }
        }
        binding.recyclerViewQuran.apply {
            adapter = TilawaAdapter(onItemClickListener)
        }

        return binding.root
    }
}