package com.example.mintsocialcompose.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.mintsocialcompose.ui.theme.MainLight

@Composable
fun SplashBody(onAnimationComplete: () -> Unit) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.Asset("animations/blog-animation.json")
    )
    val progress by animateLottieCompositionAsState(composition)

    if (progress == 1f) { // if 1f, lottie animation is complete
        LaunchedEffect(progress) { // keeps track of progress state and is lifecycle aware
            onAnimationComplete() // navigates to login screen
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MainLight)
    ) {
        LottieAnimation(
            composition,
            progress,
        )
    }
}