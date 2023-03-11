package com.example.to_do_compose.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.example.to_do_compose.ui.screens.splash.SplashScreen
import com.example.to_do_compose.utils.Constants.SPLASH_SCREEN

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.splashComposable(
    navigateToListScreen: () -> Unit
) {
    composable(
        exitTransition = {
            slideOutVertically(
            targetOffsetY = { screenHeight -> -screenHeight},
            animationSpec = tween(2000)
        )},
        route = SPLASH_SCREEN) {
        SplashScreen(navigateToListScreen = navigateToListScreen)
    }
}