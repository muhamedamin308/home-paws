package com.example.homepaws.features.onboarding.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.homepaws.R
import com.example.homepaws.databinding.ActivityOnboardingBinding
import com.example.homepaws.features.auth.ui.SignInFragment
import com.example.homepaws.features.home.ui.HomeActivity
import com.example.homepaws.features.onboarding.viewmodel.OnBoardingViewModel
import com.example.homepaws.features.splash.SplashViewModel
import com.example.homepaws.utils.Constants
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnBoardingActivity : AppCompatActivity() {
    private val splashViewModel by viewModel<SplashViewModel>()
    private val onBoardingViewModel by viewModel<OnBoardingViewModel>()
    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_HomePaws)

        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Keep splash until loading completes
        installSplashScreen().apply {
            setKeepOnScreenCondition { splashViewModel.isLoading.value }
        }

        // Observe app state and navigate accordingly
        lifecycleScope.launch {
            onBoardingViewModel.appState.collectLatest { state ->
                when (state) {
                    Constants.HOME_ACTIVITY_ID -> {
                        startActivity(Intent(this@OnBoardingActivity, HomeActivity::class.java))
                        finish()
                    }

                    Constants.SIGN_IN_ACTIVITY_ID -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, SignInFragment())
                            .commit()
                    }

                    else -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, ScreenOnboarding())
                            .commit()
                    }
                }
            }
        }
    }
}