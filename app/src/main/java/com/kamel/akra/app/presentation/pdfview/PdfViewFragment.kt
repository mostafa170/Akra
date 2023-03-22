package com.kamel.akra.app.presentation.pdfview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import com.github.barteksc.pdfviewer.util.FitPolicy
import com.kamel.akra.databinding.FragmentPdfViewBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class PdfViewFragment : Fragment() {

    private val viewModel: PdfViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPdfViewBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.executePendingBindings()
        val arguments = PdfViewFragmentArgs.fromBundle(requireArguments())
        viewModel.setSurah(arguments.surah)

        viewModel.back.observe(viewLifecycleOwner) {
            if (it != null && it) {
                findNavController().navigateUp()
                viewModel.onBackNavigated()
            }
        }

        viewModel.currentSurah.observe(viewLifecycleOwner) { surah ->
            if (surah != null) {
                loadPdf(
                    binding,
                    surah.id,
                )
            }
        }
        return binding.root
    }

    private fun loadPdf(binding: FragmentPdfViewBinding, surahId: Int, pageNumber: Int = 0){
        binding.pdfView.fromAsset(String.format(Locale.ENGLISH, "pdfs/%d.pdf", surahId))
            .enableSwipe(true) // allows to block changing pages using swipe
            .enableDoubletap(true)
            .defaultPage(pageNumber)
            .enableAntialiasing(true) // improve rendering a little bit on low-res screens
            .pageFitPolicy(FitPolicy.BOTH)
            .fitEachPage(true)
            .autoSpacing(true)
            .spacing(0)
            .scrollHandle(DefaultScrollHandle(requireContext()))
            .pageSnap(true) // snap pages to screen boundaries
            .pageFling(true) // make a fling change only a single page like ViewPager
            .load()
    }

}