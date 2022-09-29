package com.kamel.akra.app.presentation.sebha

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.viewModels
import com.kamel.akra.databinding.FragmentSebhaBinding
import androidx.navigation.fragment.findNavController
import com.kamel.akra.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SebhaFragment : Fragment() {

    private val viewModel: SebhaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentSebhaBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.executePendingBindings()
        val otherSeriesList: MutableList<String> = resources.getStringArray(R.array.tsbeh_array).toMutableList()

        viewModel.back.observe(viewLifecycleOwner) {
            if (it != null && it) {
                findNavController().navigateUp()
                viewModel.onBackNavigated()
            }
        }

        viewModel.selectSebhak.observe(viewLifecycleOwner) {
            if (it != null && it) {
                val popup = PopupMenu(
                    requireContext(), binding.textViewSebhaName
                )
                for (i in otherSeriesList.indices) {
                    popup.menu.add(
                        i, i, i,
                        otherSeriesList[i]
                    )
                }
                popup.setOnMenuItemClickListener { item ->
                    binding.textViewSebhaName.text = otherSeriesList[item.itemId]
                    viewModel.restSebha(false)
                    false
                }
                popup.show()
                viewModel.onSelectionSebhaDone()
            }
        }

        return binding.root
    }

}