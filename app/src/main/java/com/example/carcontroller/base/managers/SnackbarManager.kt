package com.example.carcontroller.base.managers

import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import com.example.carcontroller.domain.enums.SnackbarType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object SnackbarManager {

    val snackbarHostState = SnackbarHostState()

    fun showSnackbar(
        message: String,
        type: SnackbarType,
        coroutineScope: CoroutineScope,
        duration: SnackbarDuration = SnackbarDuration.Short
    ) {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(
                message = message,
                duration = duration,
                actionLabel = type.name
            )
        }
    }
}