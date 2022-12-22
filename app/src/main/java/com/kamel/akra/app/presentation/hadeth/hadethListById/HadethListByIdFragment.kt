package com.kamel.akra.app.presentation.hadeth.hadethListById

import android.content.ContextWrapper
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kamel.akra.app.presentation.hadeth.HadethViewModel
import com.kamel.akra.app.presentation.main.MainActivityEventsListener
import com.kamel.akra.app.utilsView.PaginationScrollListener
import com.kamel.akra.databinding.FragmentHadethListByIdBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HadethListByIdFragment : Fragment() {
    private val viewModel: HadethViewModel by viewModels()
    private lateinit var hadethListByIdAdapter : HadethListByIdAdapter

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
        viewModel.getHadethListByIdApi(args.hadethListById)

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

        val onItemClickListener = OnHadethListByIdClickListener{
            if (it != null && this::hadethListByIdAdapter.isInitialized){
                findNavController().navigate(HadethListByIdFragmentDirections.actionToHadethDetailsFragment(it.id))
            }
        }

        hadethListByIdAdapter = HadethListByIdAdapter(onItemClickListener).also {
            binding.recyclerViewHadeth.adapter = it
        }
        viewModel.hadethListById.observe(viewLifecycleOwner){
            if (it !=null){
                hadethListByIdAdapter.submitList(it)
            }
        }
        binding.recyclerViewHadeth.addOnScrollListener(object : PaginationScrollListener(binding.recyclerViewHadeth.layoutManager as LinearLayoutManager){
            override fun isLastPage(): Boolean {
                return viewModel.isLastPage
            }

            override fun isLoading(): Boolean {
                return viewModel.isLoading
            }

            override fun loadMoreItems() {
                viewModel.currentPage += 1
                viewModel.isLoading = true
                if (!viewModel.isLastPage)
                    viewModel.getHadethListByIdApi(args.hadethListById)
                Log.e("TAG", "currentPage: ${viewModel.currentPage}" )
            }

        })

        return binding.root
    }

}