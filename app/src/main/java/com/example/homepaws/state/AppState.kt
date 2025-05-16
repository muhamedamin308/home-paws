package com.example.homepaws.state

/**
 * @author Muhamed Amin Hassan on 14,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */

sealed class AppState<T>(
    val data: T? = null,
    val message: String? = null,
) {
    class Ideal<T> : AppState<T>()
    class Loading<T> : AppState<T>()
    class Success<T>(data: T) : AppState<T>(data = data)
    class Error<T>(message: String) : AppState<T>(message = message)
}