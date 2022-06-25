package com.example.carcontroller.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carcontroller.data.MockData
import com.example.carcontroller.model.DoorsLockStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    data class AlertDialogState(
        val title: String,
        val message: String,
        val action: DialogAction
    )

    enum class DialogAction {
        LOCK,
        UNLOCK
    }

    companion object {
        const val DOORS_LOCKING_DELAY = 5000L
    }

    val vehicles by mutableStateOf(MockData.vehicles)

    var currentVehicle by mutableStateOf(vehicles.first())
        private set

    var alertDialogState by mutableStateOf<AlertDialogState?>(null)
        private set

    fun onCurrentVehicleChange(page: Int) {
        currentVehicle = vehicles[page]
    }

    fun askForLockDoors() {
        if (!currentVehicle.doors.isBusy && currentVehicle.doors.status != DoorsLockStatus.LOCKED) {
            alertDialogState = AlertDialogState(
                title = "Are you sure?",
                message = "Please confirm that you want to lock the doors of \"${currentVehicle.model}\"",
                action = DialogAction.LOCK
            )
        }
    }

    fun askForUnlockDoors() {
        if (!currentVehicle.doors.isBusy && currentVehicle.doors.status != DoorsLockStatus.UNLOCKED) {
            alertDialogState = AlertDialogState(
                title = "Are you sure?",
                message = "Please confirm that you want to unlock the doors of \"${currentVehicle.model}\"",
                action = DialogAction.LOCK
            )
        }
    }

    fun onLockDoors() {
        viewModelScope.launch {
            startDoorsLocking()
            delay(DOORS_LOCKING_DELAY)
            lockDoors()
        }
    }

    fun onUnlockDoors() {
        viewModelScope.launch {
            startDoorsUnlocking()
            delay(DOORS_LOCKING_DELAY)
            unlockDoors()
        }
    }

    fun onDialogDismissed() {
        alertDialogState = null
    }

    private fun startDoorsLocking() {
        currentVehicle = currentVehicle.copy(
            doors = currentVehicle.doors.copy(status = DoorsLockStatus.LOCKING)
        )
    }

    private fun lockDoors() {
        currentVehicle = currentVehicle.copy(
            doors = currentVehicle.doors.copy(status = DoorsLockStatus.LOCKED)
        )
    }

    private fun startDoorsUnlocking() {
        currentVehicle = currentVehicle.copy(
            doors = currentVehicle.doors.copy(status = DoorsLockStatus.UNLOCKING)
        )
    }

    private fun unlockDoors() {
        currentVehicle = currentVehicle.copy(
            doors = currentVehicle.doors.copy(status = DoorsLockStatus.UNLOCKED)
        )
    }
}