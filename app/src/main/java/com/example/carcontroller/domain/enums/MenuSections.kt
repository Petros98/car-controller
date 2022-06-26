package com.example.carcontroller.domain.enums

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.sharp.DirectionsCar
import androidx.compose.material.icons.sharp.PinDrop
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.carcontroller.R

enum class MenuSections(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
) {
    HOME(R.string.home, Icons.Outlined.Home, "home"),
    VEHICLE(R.string.vehicle, Icons.Sharp.DirectionsCar, "vehicle"),
    LOCATION(R.string.location, Icons.Sharp.PinDrop, "location"),
    SETTINGS(R.string.settings, Icons.Outlined.Settings, "settings")
}