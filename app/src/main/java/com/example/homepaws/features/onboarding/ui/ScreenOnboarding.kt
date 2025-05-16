package com.example.homepaws.features.onboarding.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.homepaws.R
import com.example.homepaws.databinding.ScreenOnboardingMainBinding
import com.example.homepaws.features.base.BaseFragment
import com.example.homepaws.features.onboarding.navigation.OnBoardingFirstScreen
import com.example.homepaws.features.onboarding.navigation.OnBoardingSecondScreen
import com.example.homepaws.features.onboarding.navigation.OnBoardingThirdScreen
import com.example.homepaws.features.onboarding.navigation.ViewPagerAdapter
import com.example.homepaws.features.onboarding.viewmodel.OnBoardingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Muhamed Amin Hassan on 13,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */
@Suppress("UNCHECKED_CAST")
class ScreenOnboarding : BaseFragment<ScreenOnboardingMainBinding>() {
    private val viewModel by viewModel<OnBoardingViewModel>()
    override fun inflateBinding(layoutInflater: LayoutInflater): ScreenOnboardingMainBinding =
        ScreenOnboardingMainBinding.inflate(layoutInflater)


    override fun onViewReady() {
        super.onViewReady()

        val screens = arrayListOf(
            OnBoardingFirstScreen(),
            OnBoardingSecondScreen(),
            OnBoardingThirdScreen()
        )
        val adapter = ViewPagerAdapter(
            screens as ArrayList<Fragment>,
            childFragmentManager,
            lifecycle = lifecycle
        )
        binding.viewPager.adapter = adapter
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        val indicator = binding.circleIndicator
        indicator.setViewPager(binding.viewPager)
        adapter.registerAdapterDataObserver(indicator.adapterDataObserver)
        binding.getStartedButton.setOnClickListener {
            viewModel.clickOnGetStartedButton()
            findNavController().navigate(R.id.action_screenOnboarding_to_signInFragment)
        }
    }
}