package com.example.carcontroller.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.carcontroller.domain.enums.ViewState
import com.example.carcontroller.ui.theme.Brown
import com.example.carcontroller.ui.theme.ELEVATION_SMALL

@Composable
fun ActionButton(
    state: ViewState,
    modifier: Modifier = Modifier,
    text: String? = null,
    @DrawableRes iconId: Int? = null,
    onClick: () -> (Unit)
) {
    Box(
        modifier = modifier
            .shadow(elevation = ELEVATION_SMALL, CircleShape)
            .background(state.backgroundColor)
            .aspectRatio(1f)
            .clickable(state != ViewState.LOADING || state != ViewState.DISABLED) {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        if (state == ViewState.LOADING) {
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize(),
                color = Brown,
                strokeWidth = 2.dp
            )
        } else {
            if (iconId != null) {
                Icon(
                    painter = painterResource(iconId),
                    contentDescription = text,
                    tint = MaterialTheme.colors.onPrimary
                )
            } else {
                Text(text = text.orEmpty(), style = MaterialTheme.typography.button)
            }
        }
    }
}