package com.example.mintsocialcompose.ui.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.sp

@Composable
fun MintAlertDialog(
    title: String,
    body: String,
    onConfirm: () -> Unit = {},
    confirmText: String? = null,
    onDismiss: () -> Unit = {},
    dismissText: String? = null,
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
            if (confirmText != null) {
                TextButton(
                    onClick = {
                        onConfirm()
                    }
                ) {
                    Text(text = confirmText)
                }
            }
        },
        dismissButton = {
            if (dismissText != null) {
                TextButton(
                    onClick = {
                        onDismiss()
                    }
                ) {
                    Text(text = dismissText)
                }
            }
        },
        modifier = Modifier.semantics {
            contentDescription = "dialog"
        }
    )
}