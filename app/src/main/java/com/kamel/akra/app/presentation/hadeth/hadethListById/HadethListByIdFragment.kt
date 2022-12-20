package com.kamel.akra.app.presentation.hadeth.hadethListById

import android.content.ContextWrapper
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kamel.akra.R
import com.kamel.akra.app.presentation.hadeth.HadethViewModel
import com.kamel.akra.app.presentation.main.MainActivityEventsListener
import com.kamel.akra.databinding.FragmentHadethBinding
import com.kamel.akra.databinding.FragmentHadethListByIdBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HadethListByIdFragment : Fragment() {
    private val viewModel: HadethViewModel by viewModels()

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
        val binding = FragmentHadethListByIdBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.executePendingBindings()
        val args = HadethListByIdFragmentArgs.fromBundle(requireArguments())
        Log.e("TAG", "HadethListByIdFragment: ${args.hadethListById}" )
        viewModel.getHadethDetailsApi(args.hadethListById)

        viewModel.back.observe(viewLifecycleOwner){
            if (it !=null && it){
                findNavController().navigateUp()
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