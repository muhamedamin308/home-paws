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
import com.example.homepaws.databinding.FragmentSignUpBinding
import com.example.homepaws.features.auth.viewmodel.AuthViewModel
import com.example.homepaws.features.base.BaseFragment
import com.example.homepaws.state.AppState
import com.example.homepaws.utils.RegisterFieldState
import com.example.homepaws.utils.RegisterValidation
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Muhamed Amin Hassan on 13,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */
class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {

    private val viewModel by viewModel<AuthViewModel>()

    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentSignUpBinding =
        FragmentSignUpBinding.inflate(layoutInflater)

    override fun onViewReady() {
        super.onViewReady()
        setupListeners()
        observeViewModel()
    }

    private fun setupListeners() = with(binding) {
        tvSignin.setOnClickListener { navigateToLogin() }
        btnSignUp.setOnClickListener { attemptSignUp() }

        etEmailValue.doOnTextChanged { _, _, _, _ -> etEmail.error = null }
        etPasswordValue.doOnTextChanged { _, _, _, _ -> etPassword.error = null }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.registerValidation
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect(::handleValidationErrors)
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.signUpState.collect(::handleSignUpState)
            }
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

    private fun handleSignUpState(state: AppState<FirebaseUser>) {
        when (state) {
            is AppState.Error -> showToast(state.message)
            is AppState.Success -> showToast("Signed up as ${state.data?.email}")
            is AppState.Ideal, is AppState.Loading -> Unit
        }
    }

    private fun attemptSignUp() {
        val email = binding.etEmailValue.text.toString()
        val name = binding.etNameValue.text.toString()
        val password = binding.etPasswordValue.text.toString()
        viewModel.signUp(name, email, password)
    }

    private fun navigateToLogin() {
        findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
    }

    private fun showToast(message: String?) {
        Toast.makeText(context, message ?: "Unknown error", Toast.LENGTH_SHORT).show()
    }
}