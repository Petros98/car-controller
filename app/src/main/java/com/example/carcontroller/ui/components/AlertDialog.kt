package com.example.carcontroller.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.carcontroller.R
import com.example.carcontroller.ui.theme.Blue
import com.example.carcontroller.ui.theme.PADDING_MEDIUM
import com.example.carcontroller.ui.theme.Shapes
import com.example.carcontroller.ui.theme.White

@Composable
fun AlertDialog(
    title: String,
    message: String?,
    modifier: Modifier = Modifier,
    negativeButtonText: String = stringResource(R.string.cancel_button_title),
    positiveButtonText: String = stringResource(R.string.yes_button_title),
    onDismiss: () -> Unit = {},
    onPositiveButtonClick: () -> Unit = {}
) {
    AlertDialog(
        shape = Shapes.medium,
        title = {
            HeaderText(text = title)
        },
        text = {
            Text(text = message.orEmpty(), style = MaterialTheme.typography.h3)
        },
        onDismissRequest = onDismiss,
        buttons = {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(PADDING_MEDIUM),
                horizontalArrangement = Arrangement.End
            ) {
                NegativeButton(
                    negativeButtonText = negativeButtonText,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    onDismiss = onDismiss
                )
                PositiveButton(
                    onPositiveButtonClick = onPositiveButtonClick,
                    positiveButtonText = positiveButtonText
                )
            }
        }
    )
}

@Composable
private fun PositiveButton(
    positiveButtonText: String,
    modifier: Modifier = Modifier,
    onPositiveButtonClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onPositiveButtonClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Blue,
            contentColor = White
        )
    ) {
        Text(text = positiveButtonText)
    }
}

@Composable
private fun NegativeButton(
    negativeButtonText: String,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit
) {
    TextButton(modifier = modifier, onClick = onDismiss) {
        Text(
            text = negativeButtonText,
            style = MaterialTheme.typography.button,
            color = Blue
        )
    }
}