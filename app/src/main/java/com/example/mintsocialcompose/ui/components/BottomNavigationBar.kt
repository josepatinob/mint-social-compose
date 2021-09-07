package com.example.mintsocialcompose.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Help
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mintsocialcompose.MintScreen
import com.example.mintsocialcompose.ui.theme.Accent
import com.example.mintsocialcompose.ui.theme.Main

@Composable
fun BottomNavigationBar(
    navItems: List<MintScreen>,
    onItemClicked: (MintScreen) -> Unit,
    currentScreen: MintScreen
) {
    BottomNavigation(backgroundColor = Color.White, elevation = 50.dp) {
        navItems.forEach { item ->
            BottomNavigationItem(
                selected = currentScreen.name == item.name,
                onClick = { onItemClicked(item) },
                icon = {
                    Icon(
                        imageVector = item.icon ?: Icons.Filled.Help,
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)
                    )
                },
                selectedContentColor = Accent,
                unselectedContentColor = Main,
                alwaysShowLabel = false,
            )
        }
    }
}