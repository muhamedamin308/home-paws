package com.example.homepaws.di

import com.example.homepaws.features.auth.viewmodel.AuthViewModel
import com.example.homepaws.features.home.viewmodel.HomeViewModel
import com.example.homepaws.features.onboarding.viewmodel.OnBoardingViewModel
import com.example.homepaws.features.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Muhamed Amin Hassan on 13,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */

val viewModelModule = module {
    viewModel { SplashViewModel() }
    viewModel { OnBoardingViewModel(get(), get()) }
    viewModel {
        AuthViewModel(
            get(),
            get(),
            get()
        )
    }
    viewModel {
        HomeViewModel(
            get(),
            get()
        )
    }
}