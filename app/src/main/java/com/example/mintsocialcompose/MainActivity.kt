package com.example.mintsocialcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.example.mintsocialcompose.ui.components.ActionBar
import com.example.mintsocialcompose.ui.components.BottomNavigationBar
import com.example.mintsocialcompose.ui.theme.MintSocialComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainActivityViewModel: MainActivityViewModel by viewModels<MainActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MintSocialComposeTheme {
                MintSocialApp(
                    mainActivityViewModel
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mainActivityViewModel.checkUserAuthStatus()
    }
}

@Composable
fun MintSocialApp(
    viewModel: MainActivityViewModel
) {
    val allScreens = MintScreen.values().toList()
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = MintScreen.fromRoute(
        backStackEntry.value?.destination?.route
    )

    val isSignedIn: Boolean by viewModel.isSignedIn.observeAsState(false)
    val startDestination =
        if (!isSignedIn) MintScreen.Login.name else MintScreen.BlogList.name

    Scaffold(
        topBar = {
            if (currentScreen.supportsActionBar) {
                ActionBar(
                    currentScreen = currentScreen,
                    onNavigationUp = { navController.navigateUp() })
            }
        },
        bottomBar = {
            if (currentScreen.isHomeDestination) {
                BottomNavigationBar(
                    navItems = allScreens.filter { it.isHomeDestination },
                    onItemClicked = { screen ->
                        navController.navigate(screen.name)
                    },
                    currentScreen = currentScreen
                )
            }
        }
    ) { innerPadding ->
        MintSocialNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            startDestination = startDestination
        )
    }
}