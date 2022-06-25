package com.example.carcontroller.model

data class Engine(
    val status: EngineStatus
) {
    val state: ViewState
        get() = when (status) {
            EngineStatus.LOADING -> ViewState.LOADING
            EngineStatus.ACTIVE -> ViewState.ACTIVE
            EngineStatus.INACTIVE -> ViewState.ENABLED
        }
}

enum class EngineStatus {
    ACTIVE,
    LOADING,
    INACTIVE
}
