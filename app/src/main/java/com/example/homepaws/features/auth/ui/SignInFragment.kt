package com.example.homepaws.features.auth.ui

import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.homepaws.R
import com.example.homepaws.data.model.response.AuthResponse
import com.example.homepaws.databinding.FragmentSignInBinding
import com.example.homepaws.features.auth.viewmodel.AuthViewModel
import com.example.homepaws.features.base.BaseFragment
import com.example.homepaws.state.AppState
import com.example.homepaws.utils.RegisterFieldState
import com.example.homepaws.utils.RegisterValidation
import com.example.homepaws.utils.log
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Muhamed Amin Hassan on 13,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */
class SignInFragment : BaseFragment<FragmentSignInBinding>() {

    private val viewModel by viewModel<AuthViewModel>()

    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentSignInBinding =
        FragmentSignInBinding.inflate(layoutInflater)

    override fun onViewReady() {
        super.onViewReady()
        setupListeners()
        observeViewModel()
        viewModel.getAccessToken()
    }

    private fun setupListeners() = with(binding) {
        tvSignup.setOnClickListener { navigateToSignUp() }
        btnSignIn.setOnClickListener { attemptSignIn() }

        etEmailValue.doOnTextChanged { _, _, _, _ -> etEmail.error = null }
        etPasswordValue.doOnTextChanged { _, _, _, _ -> etPassword.error = null }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.signInState.collect { state -> handleSignInState(state) }
                }
                launch {
                    viewModel.authState.collect { result -> handleAuthResult(result) }
                }
            }
        }
        lifecycleScope.launch {
            viewModel.registerValidation
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect(::handleValidationErrors)
        }
    }

    private fun handleValidationErrors(state: RegisterFieldState) {
        with(binding) {
            (state.email as? RegisterValidation.Failed)?.let {
                etEmail.apply {
                    requestFocus()
                    error = it.message
                }
            }

            (state.password as? RegisterValidation.Failed)?.let {
                etPassword.apply {
                    requestFocus()
                    error = it.message
                }
            }
        }
    }

    private fun handleSignInState(state: AppState<FirebaseUser>) {
        when (state) {
            is AppState.Error -> showToast(state.message ?: "Unknown error")
            is AppState.Success -> showToast("Signed in as ${state.data?.email}")
            is AppState.Loading, is AppState.Ideal -> Unit
            is AppState.Ideal<*> -> TODO()
        }
    }

    private fun handleAuthResult(result: AppState<AuthResponse>?) {
        result!!.data?.let {
            log("AccessToken", it.accessToken)
            showToast("Access Token: ${it.accessToken}")
        } ?: {
            log("AccessTokenError", result.message)
            showToast(result.message ?: "Unknown error")
        }
    }

    private fun attemptSignIn() {
        val email = binding.etEmailValue.text.toString()
        val password = binding.etPasswordValue.text.toString()
        viewModel.signIn(email, password)
    }

    private fun navigateToSignUp() {
        findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}