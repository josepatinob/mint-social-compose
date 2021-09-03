package com.example.mintsocialcompose.ui.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.example.mintsocialcompose.ui.theme.Main

@Composable
fun ActionBar() {
    TopAppBar(title = {
        Text(text = "Mint Social")
    }, backgroundColor = Main)
}