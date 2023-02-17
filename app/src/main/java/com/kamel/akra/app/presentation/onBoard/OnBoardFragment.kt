package com.kamel.akra.app.presentation.onBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.kamel.akra.R
import com.kamel.akra.databinding.FragmentOnboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardFragment : Fragment() {

    private val viewModel: OnBoardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentOnboardBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.executePendingBindings()

        val listOf = listOf(
            IntroItem(getString(R.string.label_introWelcome1_title),getString(R.string.label_introWelcome1_content), R.drawable.ic_intro_1),
            IntroItem(getString(R.string.label_introWelcome2_title), getString(R.string.label_introWelcome2_content),R.drawable.ic_intro_2),
            IntroItem(getString(R.string.label_introWelcome3_title),getString(R.string.label_introWelcome3_content) ,R.drawable.ic_intro_3),
        )
        val onBoardAdapter = OnBoardPagerAdapter()
        onBoardAdapter.submitList(listOf)

        binding.pagerIntros.apply {
            adapter = onBoardAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrollStateChanged(state: Int) {}
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
                override fun onPageSelected(position: Int) {
                    viewModel.setCurrentIntroItem(position)
                }
            })
        }

        viewModel.currentIntroItem.observe(viewLifecycleOwner) {
            if (it != null)
                binding.pagerIntros.setCurrentItem(it, true)
        }
        viewModel.skipIntro.observe(viewLifecycleOwner) {
            if (it != null && it) {
                this.findNavController()
                    .navigate(OnBoardFragmentDirections.actionToHomeFragment())
                viewModel.onIntrosSkipped()
            }
        }

        return binding.root
    }
}