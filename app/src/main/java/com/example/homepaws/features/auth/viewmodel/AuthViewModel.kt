package com.example.homepaws.features.auth.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.homepaws.data.model.response.AuthResponse
import com.example.homepaws.data.repo.AuthRepository
import com.example.homepaws.data.service.firebase.AuthenticationService
import com.example.homepaws.features.base.BaseViewModel
import com.example.homepaws.state.AppState
import com.example.homepaws.utils.Cache
import com.example.homepaws.utils.RegisterFieldState
import com.example.homepaws.utils.RegisterValidation
import com.example.homepaws.utils.validateEmail
import com.example.homepaws.utils.validatePassword
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @author Muhamed Amin Hassan on 14,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */
class AuthViewModel(
    private val authService: AuthenticationService,
    private val authRepository: AuthRepository,
    private val tokenStorage: Cache.TokenStorage,
) : BaseViewModel() {
    private val _signUpState =
        MutableStateFlow<AppState<FirebaseUser>>(AppState.Ideal())
    val signUpState = _signUpState.asStateFlow()

    private val _signInState =
        MutableStateFlow<AppState<FirebaseUser>>(AppState.Ideal())
    val signInState = _signInState.asStateFlow()

    private val _registerValidation = Channel<RegisterFieldState>()
    val registerValidation = _registerValidation.receiveAsFlow()

    private val _authState = MutableStateFlow<AppState<AuthResponse>?>(null)
    val authState = _authState.asStateFlow()

    fun getAccessToken() {
        viewModelScope.launch {
            val cachedToken = tokenStorage.getToken()
            Log.i("TOKEN_CACHE", "$cachedToken")
            cachedToken?.let {
                _authState.value = AppState.Success(
                    AuthResponse(
                        cachedToken,
                        3600,
                        "Bearer"
                    )
                )
                return@launch
            }

            val result = authRepository.fetchAccessToken()
            _authState.value = result

            Log.i("TOKEN_RESULT", "${result.data?.accessToken}")
            result.data?.accessToken?.takeIf { it.isNotEmpty() }?.let { token ->
                tokenStorage.saveToken(token, result.data.expiresIn.toLong())
            }
        }
    }

    fun signUp(name: String, email: String, password: String) {
        if (accountValidation(email, password)) {
            updateAppState(_signUpState, AppState.Loading())
            viewModelScope.launch {
                val result = authService.signUp(name = name, email = email, password = password)
                result.onSuccess {
                    handleResult(_signUpState, it, null)
                }.onFailure {
                    handleResult(_signUpState, null, it as Exception?)
                }
            }
        } else {
            val registerFieldsState = RegisterFieldState(
                validateEmail(email),
                validatePassword(password)
            )
            runBlocking { _registerValidation.send(registerFieldsState) }
        }
    }

    fun signIn(email: String, password: String) {
        if (accountValidation(email, password)) {
            updateAppState(_signInState, AppState.Loading())
            viewModelScope.launch {
                val result = authService.signIn(email = email, password = password)
                result.onSuccess {
                    handleResult(_signInState, it, null)
                }.onFailure {
                    handleResult(_signInState, null, it as Exception?)
                }
            }
        } else {
            val registerFieldsState = RegisterFieldState(
                validateEmail(email),
                validatePassword(password)
            )
            runBlocking { _registerValidation.send(registerFieldsState) }
        }
    }

    private fun accountValidation(email: String?, password: String): Boolean {
        val emailValidation = validateEmail(email!!)
        val passwordValidate = validatePassword(password)
        return emailValidation is RegisterValidation.Success && passwordValidate is RegisterValidation.Success
    }

}