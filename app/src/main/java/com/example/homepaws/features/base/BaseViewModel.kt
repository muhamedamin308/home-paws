package com.example.homepaws.features.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homepaws.state.AppState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 * @author Muhamed Amin Hassan on 14,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */
abstract class BaseViewModel : ViewModel() {
    protected fun <T> handleResult(
        stateFlow: MutableStateFlow<AppState<T>>,
        result: T?,
        exception: Exception?,
    ) {
        exception?.let {
            updateAppState(stateFlow, AppState.Error(it.message ?: "An error occurred"))
        } ?: updateAppState(stateFlow, AppState.Success(result!!))
    }

    protected fun <T> updateAppState(
        stateFlow: MutableStateFlow<AppState<T>>,
        newState: AppState<T>,
    ) {
        viewModelScope.launch { stateFlow.emit(newState) }
    }
}