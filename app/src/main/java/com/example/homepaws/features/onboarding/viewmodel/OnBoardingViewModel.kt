package com.example.homepaws.features.onboarding.viewmodel

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.viewModelScope
import com.example.homepaws.data.service.firebase.AuthenticationService
import com.example.homepaws.features.base.BaseViewModel
import com.example.homepaws.utils.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * @author Muhamed Amin Hassan on 13,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */
class OnBoardingViewModel(
    private val preferences: SharedPreferences,
    authService: AuthenticationService,
) : BaseViewModel() {

    private val _appState = MutableStateFlow(0)
    val appState = _appState.asStateFlow()

    init {
        val isGetStartedClicked = preferences.getBoolean(
            Constants.SHARED_PREFERENCES_KEY,
            false
        )

        if (authService.isLoggedIn) {
            viewModelScope.launch { _appState.emit(Constants.HOME_ACTIVITY_ID) }
        } else if (isGetStartedClicked) {
            viewModelScope.launch { _appState.emit(Constants.SIGN_IN_ACTIVITY_ID) }
        } else Unit
    }

    fun clickOnGetStartedButton() {
        preferences.edit {
            putBoolean(
                Constants.SHARED_PREFERENCES_KEY,
                true
            ).apply()
        }
    }
}