package com.example.homepaws.features.onboarding.navigation

import android.view.LayoutInflater
import com.example.homepaws.databinding.ScreenOnboarding1Binding
import com.example.homepaws.features.base.BaseFragment

/**
 * @author Muhamed Amin Hassan on 13,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */
class OnBoardingFirstScreen : BaseFragment<ScreenOnboarding1Binding>() {
    override fun inflateBinding(layoutInflater: LayoutInflater): ScreenOnboarding1Binding =
        ScreenOnboarding1Binding.inflate(layoutInflater)
}