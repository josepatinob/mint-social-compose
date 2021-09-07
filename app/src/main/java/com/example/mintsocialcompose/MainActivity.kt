package com.example.mintsocialcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDirections
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.airbnb.lottie.compose.*
import com.example.mintsocialcompose.ui.blogdetail.BlogDetailBody
import com.example.mintsocialcompose.ui.bloglist.BlogListBody
import com.example.mintsocialcompose.ui.components.ActionBar
import com.example.mintsocialcompose.ui.components.BottomNavigationBar
import com.example.mintsocialcompose.ui.createblog.CreateBlogBody
import com.example.mintsocialcompose.ui.login.LoginBody
import com.example.mintsocialcompose.ui.profile.ProfileBody
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
        topBar = {
            if (currentScreen.hasAppBar) {
                ActionBar(
                    currentScreen = currentScreen,
                    onNavigationUp = { navController.navigateUp() })
            }
        },
        bottomBar = {
            if (currentScreen.homeDestination) {
                BottomNavigationBar(
                    navItems = allScreens.filter { it.homeDestination },
                    onItemClicked = { screen ->
                        navController.navigate(screen.name) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    },
                    currentScreen = currentScreen
                )
            }
        }
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
            BlogListBody(onItemClick = { blogId ->
                navController.navigate(
                    "${MintScreen.BlogDetail.name}/$blogId"
                )
            })
        }
        composable(MintScreen.CreateAccount.name) {
            CreateAccountBody()
        }
        composable(
            "${MintScreen.BlogDetail.name}/{blogId}",
            arguments = listOf(
                navArgument("blogId") {
                    type = NavType.StringType
                },
            ),
        ) { entry ->
            val blogId = entry.arguments?.getString("blogId")
            // TODO: we could fetch blog detail here and pass in to blog detail screen
            BlogDetailBody(
                blogId = blogId ?: "",
                onProfileClick = { navController.navigate(MintScreen.Profile.name) })
        }
        composable(MintScreen.Profile.name) {
            ProfileBody(onItemClick = { blogId ->
                navController.navigate(
                    "${MintScreen.BlogDetail.name}/$blogId"
                )
            })
        }
        composable(MintScreen.CreateBlog.name) {
            CreateBlogBody()
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