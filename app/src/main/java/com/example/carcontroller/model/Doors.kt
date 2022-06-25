package com.example.carcontroller.model

data class Doors(
    val status: DoorsLockStatus
) {
    val isBusy: Boolean
        get() = when (status) {
            DoorsLockStatus.LOCKING, DoorsLockStatus.UNLOCKING -> true
            else -> false
        }

    val unlockedState: ViewState
        get() = when (status) {
            DoorsLockStatus.LOCKING -> ViewState.DISABLED
            DoorsLockStatus.UNLOCKING -> ViewState.LOADING
            DoorsLockStatus.LOCKED -> ViewState.ENABLED
            DoorsLockStatus.UNLOCKED -> ViewState.ACTIVE
        }

    val lockedState: ViewState
        get() = when (status) {
            DoorsLockStatus.LOCKING -> ViewState.LOADING
            DoorsLockStatus.UNLOCKING -> ViewState.DISABLED
            DoorsLockStatus.LOCKED -> ViewState.ACTIVE
            DoorsLockStatus.UNLOCKED -> ViewState.ENABLED
        }
}

enum class DoorsLockStatus(val displayText: String) {
    LOCKED("Locked"),
    LOCKING("..."),
    UNLOCKING("..."),
    UNLOCKED("Unlocked"),
}