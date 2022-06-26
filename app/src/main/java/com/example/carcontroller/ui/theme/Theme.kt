package com.example.carcontroller.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
    primary = Black,
    primaryVariant = Brown,
    secondary = Gray,
    onPrimary = White,
    background = LightGray,
    surface = White
)

@Composable
fun CarControllerTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}