package com.kamel.akra.app.presentation.azkar.azkarCategoryList

import android.content.ContextWrapper
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kamel.akra.R
import com.kamel.akra.app.presentation.azkar.AzkarViewModel
import com.kamel.akra.app.presentation.main.MainActivityEventsListener
import com.kamel.akra.databinding.FragmentAzkarCategoryListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AzkarCategoryListFragment : Fragment() {

    private val viewModel: AzkarViewModel by viewModels()

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
        val binding = FragmentAzkarCategoryListBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.executePendingBindings()
        val args = AzkarCategoryListFragmentArgs.fromBundle(requireArguments())
        viewModel.getAzkarFromAsset()
        val adapterAzkar = AzkarAdapter()
        when (args.position){
            0 -> binding.textViewPageName.text = getText(R.string.label_morning)
            1 -> binding.textViewPageName.text = getText(R.string.label_evening)
            2 -> binding.textViewPageName.text = getText(R.string.label_wake_up)
            3 -> binding.textViewPageName.text = getText(R.string.label_sleeping)
            4 -> binding.textViewPageName.text = getText(R.string.label_doaa_nabwy)
            5 -> binding.textViewPageName.text = getText(R.string.label_tsabeh)
            6 -> binding.textViewPageName.text = getText(R.string.label_after_tasleem)
            7 -> binding.textViewPageName.text = getText(R.string.label_doaa_quran)
        }
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

        viewModel.azkarCategory.observe(viewLifecycleOwner){
            if (it != null){
                when (args.position){
                    0 -> adapterAzkar.submitList(it.morning)
                    1 -> adapterAzkar.submitList(it.evening)
                    2 -> adapterAzkar.submitList(it.wakeUp)
                    3 -> adapterAzkar.submitList(it.sleep)
                    4 -> adapterAzkar.submitList(it.doaaNabwy)
                    5 -> adapterAzkar.submitList(it.tasabeh)
                    6 -> adapterAzkar.submitList(it.azkarAfterPrayer)
                    7 -> adapterAzkar.submitList(it.doaaQuran)
                }
            }
        }
        binding.recyclerViewAzkar.apply {
            adapter = adapterAzkar
        }

        return binding.root
    }
}