package com.example.mintsocialcompose.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import com.example.mintsocialcompose.MintScreen
import com.example.mintsocialcompose.ui.theme.Main

@Composable
fun ActionBar(currentScreen: MintScreen, onNavigationUp: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = currentScreen.title)
        },
        backgroundColor = Main,
        navigationIcon = {
            IconButton(onClick = onNavigationUp) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
        }
    )
}