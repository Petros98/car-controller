package com.example.carcontroller.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.carcontroller.domain.enums.SnackbarType
import com.example.carcontroller.ui.theme.ELEVATION_SMALL
import com.example.carcontroller.ui.theme.PADDING_SMALL
import com.example.carcontroller.ui.theme.Shapes

@Composable
fun Snackbar(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.primary,
    textColor: Color = MaterialTheme.colors.onPrimary,
    elevation: Dp = ELEVATION_SMALL
) {
    SnackbarHost(
        modifier = modifier.padding(PADDING_SMALL),
        hostState = snackbarHostState,
        snackbar = { snackbarData: SnackbarData ->
            SnackbarContent(
                backgroundColor,
                textColor,
                elevation,
                snackbarData
            )
        }
    )
}

@Composable
private fun SnackbarContent(
    backgroundColor: Color,
    textColor: Color,
    elevation: Dp,
    snackbarData: SnackbarData,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .shadow(elevation, Shapes.medium)
            .background(backgroundColor),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SnackbarMessage(
            modifier = Modifier.padding(8.dp),
            message = snackbarData.message,
            textColor = textColor
        )
        val iconId = SnackbarType.getByName(snackbarData.actionLabel)?.iconId

        iconId?.let { id -> painterResource(id) }?.let {
            SnackbarActionButton(
                modifier = Modifier.padding(horizontal = 8.dp),
                icon = it
            )
        }
    }
}

@Composable
private fun SnackbarMessage(
    message: String,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.padding(8.dp),
        text = message,
        color = textColor
    )
}

@Composable
private fun SnackbarActionButton(
    icon: Painter,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier.size(20.dp),
        painter = icon,
        contentDescription = ""
    )
}
