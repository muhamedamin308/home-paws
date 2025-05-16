package com.example.homepaws.utils

import android.util.Patterns

/**
 * @author Muhamed Amin Hassan on 14,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */

sealed class RegisterValidation {
    data object Success : RegisterValidation()
    data class Failed(val message: String) : RegisterValidation()
}

data class RegisterFieldState(
    val email: RegisterValidation, val password: RegisterValidation,
)


fun validateEmail(email: String): RegisterValidation =
    if (email.isEmpty())
        RegisterValidation.Failed("Email cannot be Empty!")
    else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        RegisterValidation.Failed("Invalid Email Address!")
    else
        RegisterValidation.Success

fun validatePassword(password: String): RegisterValidation =
    if (password.isEmpty())
        RegisterValidation.Failed("Password cannot be Empty!")
    else if (password.length < 8)
        RegisterValidation.Failed("Password must be at least 8 characters!")
    else
        RegisterValidation.Success