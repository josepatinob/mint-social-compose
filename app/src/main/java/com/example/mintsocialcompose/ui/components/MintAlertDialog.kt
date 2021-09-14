package com.example.mintsocialcompose.ui.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MintAlertDialog(
    title: String,
    body: String,
    onConfirm: () -> Unit,
    confirmText: String,
    onDismiss: () -> Unit,
    dismissText: String,
    dismissOnClickAway: Boolean
) {
    AlertDialog(
        onDismissRequest = {
            if (dismissOnClickAway) {
                onDismiss()
            }
        },
        title = {
            Text(text = title, fontSize = 18.sp)
        },
        text = {
            Text(
                text = body, fontSize = 16.sp
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm()
                }
            ) {
                Text(text = confirmText)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss()
                }
            ) {
                Text(text = dismissText)
            }
        }
    )
}