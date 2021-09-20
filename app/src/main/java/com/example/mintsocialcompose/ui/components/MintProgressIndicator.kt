package com.example.mintsocialcompose.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import com.example.mintsocialcompose.ui.theme.Accent

@Composable
fun MintProgressIndicator() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(top = 50.dp)
            .fillMaxSize()
            .semantics {
                contentDescription = "Progress indicator"
            }
    ) {
        CircularProgressIndicator(modifier = Modifier.size(40.dp), color = Accent)
    }
}