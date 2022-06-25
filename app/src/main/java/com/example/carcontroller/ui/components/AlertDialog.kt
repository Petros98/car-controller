package com.example.carcontroller.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.carcontroller.R
import com.example.carcontroller.ui.theme.Blue
import com.example.carcontroller.ui.theme.White

@Composable
fun AlertDialog(
    title: String,
    message: String?,
    negativeButtonText: String = stringResource(R.string.cancel_button_title),
    positiveButtonText: String = stringResource(R.string.yes_button_title),
    onDismiss: () -> Unit = {},
    onPositiveButtonClick: () -> Unit = {}
) {
    AlertDialog(
        shape = RoundedCornerShape(8.dp),
        title = {
            HeaderText(text = title)
        },
        text = {
            Text(text = message.orEmpty(), style = MaterialTheme.typography.h3)
        },
        onDismissRequest = onDismiss,
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                NegativeButton(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    onDismiss = onDismiss,
                    negativeButtonText = negativeButtonText
                )
                PositiveButton(
                    modifier = Modifier,
                    onPositiveButtonClick = onPositiveButtonClick,
                    positiveButtonText = positiveButtonText
                )
            }
        }
    )
}

@Composable
private fun PositiveButton(
    modifier: Modifier,
    onPositiveButtonClick: () -> Unit,
    positiveButtonText: String
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
private fun NegativeButton(modifier: Modifier, onDismiss: () -> Unit, negativeButtonText: String) {
    TextButton(modifier = modifier, onClick = onDismiss) {
        Text(
            text = negativeButtonText,
            style = MaterialTheme.typography.button,
            color = Blue
        )
    }
}