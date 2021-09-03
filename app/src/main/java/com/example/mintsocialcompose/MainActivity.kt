package com.example.mintsocialcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.*
import com.example.mintsocialcompose.ui.bloglist.BlogListBody
import com.example.mintsocialcompose.ui.components.ActionBar
import com.example.mintsocialcompose.ui.login.LoginBody
import com.example.mintsocialcompose.ui.register.CreateAccountBody
import com.example.mintsocialcompose.ui.theme.MintSocialComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MintSocialComposeTheme {
                MintSocialApp()
            }
        }
    }
}

@Composable
fun MintSocialApp() {
    val allScreens = MintScreen.values().toList()
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = MintScreen.fromRoute(
        backStackEntry.value?.destination?.route
    )

    Scaffold(
        topBar = { ActionBar() },
    ) { innerPadding ->
        MintSocialNavHost(
            navController = navController, modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun MintSocialNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = MintScreen.Login.name,
        modifier = modifier
    ) {
        composable(MintScreen.Login.name) {
            LoginBody(
                onLoginClick = { navController.navigate(MintScreen.BlogList.name) },
                onGuestClick = { navController.navigate(MintScreen.BlogList.name) },
                onCreateAccountClick = { navController.navigate(MintScreen.CreateAccount.name) }
            )
        }
        composable(MintScreen.BlogList.name) {
            BlogListBody()
        }
        composable(MintScreen.CreateAccount.name) {
            CreateAccountBody()
        }
    }
}

@Composable
fun Loader() {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.Asset("animations/blog-animation.json")
    )
    val progress by animateLottieCompositionAsState(composition)
    LottieAnimation(
        composition,
        progress,
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MintSocialComposeTheme {
        MintSocialApp()
    }
}